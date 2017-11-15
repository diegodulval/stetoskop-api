/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.UseType;
import com.dulval.stetoskop.repositories.UseTypeRepository;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego Dulval
 */
@Service
public class UseTypeService {

    @Autowired
    private UseTypeRepository repo;

    public UseType create(UseType obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public UseType readById(Integer id) {
        UseType obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + UseType.class.getSimpleName());
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

    public Page<UseType> read(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public UseType update(UseType obj) {
        UseType newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(UseType newObj, UseType obj) {
        newObj.setName(obj.getName());
    }
}
