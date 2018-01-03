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
import com.dulval.stetoskop.repositories.MedicamentRepository;
import com.dulval.stetoskop.repositories.PrescriptionRepository;
import com.dulval.stetoskop.repositories.specifications.PrescriptionSpecification;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import java.util.Date;
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
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository repo;

    @Autowired
    private MedicamentRepository medRepo;

    @Autowired
    private ItemPrescriptionRepository itemRepository;

    public Prescription create(Prescription obj) {
        obj.setId(null);
        obj = repo.save(obj);
        for (ItemPrescription item : obj.getPrescriptions()) {
            Medicament med = medRepo.findOne(item.getMedicament().getId());
            if (med == null) {
                throw new ObjectNotFoundException("Não é possivél associar o Medicamento com Id = " + item.getMedicament().getId() + " , objeto não encontrado!  ");
            } else {
                item.setPrescription(obj);
                itemRepository.save(item);
            }
        }

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

    public Page<Prescription> readByCriteria(Date date, Integer doctor, Integer pacient, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        Specification where = applyCriteria(date, doctor, pacient);

        return repo.findAll(where, pageRequest);
    }

    public Specification applyCriteria(Date date, Integer doctorId, Integer pacientId) {

        Specification where = null;

        if (date != null) {
            where = addClause(where, PrescriptionSpecification.byDate(date));
        }

        if (pacientId != null && pacientId > 0L) {
            where = addClause(where, PrescriptionSpecification.wherePacient(pacientId));
        }

        if (doctorId != null && doctorId > 0L) {
            where = addClause(where, PrescriptionSpecification.whereDoctor(doctorId));
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

    public Prescription update(Prescription obj) {
        Prescription newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Prescription newObj, Prescription obj) {
        newObj.setDate(obj.getDate());
        newObj.setDescription(obj.getDescription());
        newObj.setDescription(obj.getDescription());

        itemRepository.delete(newObj.getPrescriptions());
        newObj.setPrescriptions(obj.getPrescriptions());
        itemRepository.save(newObj.getPrescriptions());
    }
}
