package com.dulval.stetoskop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.repositories.DoctorRepository;
import com.dulval.stetoskop.repositories.specifications.DoctorSpecification;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import static springfox.documentation.schema.property.BeanPropertyDefinitions.name;

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

    public Page<Doctor> readByCriteria(String nameDecoded, String emailDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Specification where = applyCriteria(nameDecoded, emailDecoded);
        return repo.findAll(where, pageRequest);
    }

    public Doctor update(Doctor obj) {
        Doctor newObj = readById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Doctor newObj, Doctor obj) {
        newObj.setName(obj.getName());
        newObj.setCrm(obj.getCrm());
        newObj.setEmail(obj.getEmail());
        newObj.setProfession(obj.getProfession());
        newObj.setPhone(obj.getPhone());
    }

    public Specification applyCriteria(String name, String email) {

        Specification where = null;

        if (name != null && !name.isEmpty()) {
            where = addClause(where, DoctorSpecification.byName(name));
        }
        if (email != null && !email.isEmpty()) {
            where = addClause(where, DoctorSpecification.byEmail(email));
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
}
