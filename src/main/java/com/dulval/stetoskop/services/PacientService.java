/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.InterationMedicament;
import com.dulval.stetoskop.domain.Medicament;
import com.dulval.stetoskop.repositories.InterationMedicamentRepository;
import com.dulval.stetoskop.repositories.MedicamentRepository;
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
public class MedicamentService {

    @Autowired
    private MedicamentRepository repo;

    @Autowired
    private InterationMedicamentRepository iterationRepository;

    public Medicament create(Medicament obj) {
        obj.setId(null);
        obj = repo.save(obj);

        for (InterationMedicament interation : obj.getInterations()) {

            Medicament med = repo.findOne(interation.getMedicament());
            if (med == null) {
                throw new ObjectNotFoundException("Não é possivél associar o Medicamento com Id = " + interation.getMedicament() + " , objeto não encontrado!  ");
            }

            interation.setMedicamentInteration(obj.getId());
            interation.setMedicament(med.getId());

        }
        iterationRepository.save(obj.getInterations());

        return obj;
    }

    public Medicament readById(Integer id) {
        Medicament obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Medicament.class.getSimpleName());
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

    public Page<Medicament> read(String nameDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Medicament update(Medicament obj) {
        Medicament newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Medicament newObj, Medicament obj) {
        newObj.setName(obj.getName());
        newObj.setApresentations(obj.getApresentations());
        newObj.setComercialNames(obj.getComercialNames());
        newObj.setInterations(obj.getInterations());
    }
}
