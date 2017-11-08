/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.Medicament;
import com.dulval.stetoskop.domain.Pacient;
import com.dulval.stetoskop.repositories.AddressRepository;
import com.dulval.stetoskop.repositories.PacientRepository;
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
public class PacientService {

    @Autowired
    private PacientRepository repo;

    @Autowired
    private AddressRepository addRepo;

    public Pacient create(Pacient obj) {

        obj.getAddress().setId(null);
        addRepo.save(obj.getAddress());

        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

    public Pacient readById(Integer id) {
        Pacient obj = repo.findOne(id);
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

    public Page<Pacient> read(String nameDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Pacient update(Pacient obj) {
        Pacient newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Pacient newObj, Pacient obj) {
        newObj.setName(obj.getName());
        newObj.setPhone(obj.getPhone());
        newObj.setProfession(obj.getProfession());
        newObj.setBirthdate(obj.getBirthdate());
    }
}
