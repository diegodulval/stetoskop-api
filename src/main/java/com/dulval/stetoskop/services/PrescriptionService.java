/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.ItemPrescription;
import com.dulval.stetoskop.domain.Medicament;
import com.dulval.stetoskop.domain.Prescription;
import com.dulval.stetoskop.repositories.ItemPrescriptionRepository;
import com.dulval.stetoskop.repositories.PrescriptionRepository;
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
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository repo;

    @Autowired
    private ItemPrescriptionRepository itemRepository;

    public Prescription create(Prescription obj) {
        obj.setId(null);
        obj = repo.save(obj);

        for (ItemPrescription item : obj.getMedicaments()) {
            Prescription prescription = repo.findOne(item.getMedicament().getId());
            if (prescription == null) {
                throw new ObjectNotFoundException("Não é possivél associar o Medicamento com Id = " + item.getMedicament().getId() + " , objeto não encontrado!  ");
            }
        }
        itemRepository.save(obj.getMedicaments());

        return obj;
    }

    public Prescription readById(Integer id) {
        Prescription obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Prescription.class.getSimpleName());
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

    public Page<Prescription> read(String nameDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Prescription update(Prescription obj) {
        Prescription newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Prescription newObj, Prescription obj) {
        newObj.setDate(obj.getDate());
        newObj.setDescription(obj.getDescription());

        itemRepository.delete(newObj.getMedicaments());
        newObj.setMedicaments(obj.getMedicaments());
        itemRepository.save(newObj.getMedicaments());
    }
}
