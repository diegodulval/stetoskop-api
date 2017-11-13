package com.dulval.stetoskop.services;

import com.dulval.stetoskop.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dulval.stetoskop.domain.Institution;
import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.services.exceptions.DataIntegrityException;
import com.dulval.stetoskop.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.dulval.stetoskop.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BCryptPasswordEncoder pe;

    public User readById(Integer id) {
        User obj = repo.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
                    + ", Tipo: " + User.class.getSimpleName());
        }
        return obj;
    }

    public User create(User obj) {
        obj.setId(null);
        obj.setPassword(pe.encode(obj.getPassword()));
        if (obj instanceof Institution) {
            Institution inst = (Institution) obj;
        } else if (obj instanceof Doctor) {
            Doctor med = (Doctor) obj;
        }

        addressService.create(obj.getAddress());

        obj = repo.save(obj);
        emailService.sendOrderConfirmationEmail(obj);
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

    public Page<User> read(String nameDecoded, String emailDecoded, Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        //return repo.findAll(nameDecoded, emailDecoded, pageRequest);
        return repo.findAll(pageRequest);
    }

}
