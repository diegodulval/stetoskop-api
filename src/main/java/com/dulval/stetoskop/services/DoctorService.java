package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.repositories.DoctorRepository;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public Doctor readById(Integer id) {
        Doctor obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + Doctor.class.getSimpleName());
        }
        return obj;
    }

    public void delete(Integer id) {
        readById(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public Page<Doctor> read(String nameDecoded, String emailDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

}
