/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.Address;
import com.dulval.stetoskop.repositories.AddressRepository;
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
public class AddressService {

    @Autowired
    private AddressRepository repo;

    public Address create(Address obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Address readById(Integer id) {
        Address obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Address.class.getSimpleName());
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

    public Page<Address> read(String nameDecoded, String emailDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Address update(Address obj) {
        Address newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Address newObj, Address obj) {
        newObj.setCity(obj.getCity());
        newObj.setNeighborhood(obj.getNeighborhood());
        newObj.setNumber(obj.getNumber());
        newObj.setStreet(obj.getStreet());
    }
}
