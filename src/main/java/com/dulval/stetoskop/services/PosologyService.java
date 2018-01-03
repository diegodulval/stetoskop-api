/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.Posology;
import com.dulval.stetoskop.repositories.PosologyRepository;
import com.dulval.stetoskop.repositories.specifications.PosologySpecification;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego Dulval
 */
@Service
public class PosologyService {

    @Autowired
    private PosologyRepository repo;

    public Posology create(Posology obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Posology readById(Integer id) {
        Posology obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Posology.class.getSimpleName());
        }
        return obj;
    }

    public void delete(Integer id) {
        readById(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há registros relacionados");
        }
    }

    public Page<Posology> readByCriteria(String descDecoded, Integer doctorId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Specification where = applyCriteria(descDecoded, doctorId);

        return repo.findAll(where, pageRequest);
    }

    public Specification applyCriteria(String desc, Integer doctorId) {

        Specification where = null;

        if (desc != null && !desc.isEmpty()) {
            where = addClause(where, PosologySpecification.byDescription(desc));
        }

        if (doctorId != null && doctorId > 0L) {
            where = addClause(where, PosologySpecification.whereDoctor(doctorId));
        }

        return where;
    }

    private Specification addClause(Specification where, Specification newClause) {
        if (where == null) {
            return where(newClause);
        } else {
            return where(where).and(newClause);
        }
    }

    public Posology update(Posology obj) {
        Posology newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Posology newObj, Posology obj) {
        newObj.setDescription(obj.getDescription());
    }
}
