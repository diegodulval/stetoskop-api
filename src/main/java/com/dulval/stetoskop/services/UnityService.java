/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.Unity;
import com.dulval.stetoskop.repositories.UnityRepository;
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
public class UnityService {

    @Autowired
    private UnityRepository repo;

    public Unity create(Unity obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Unity readById(Integer id) {
        Unity obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Unity.class.getSimpleName());
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

    public Page<Unity> read(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Unity update(Unity obj) {
        Unity newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Unity newObj, Unity obj) {
        newObj.setName(obj.getName());
    }
}
