package com.dulval.stetoskop.resources;

import com.dulval.stetoskop.domain.User;
import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.domain.Institution;
import com.dulval.stetoskop.dto.response.DoctorResponse;
import com.dulval.stetoskop.dto.response.InstitutionResponse;
import com.dulval.stetoskop.dto.response.UserResponse;
import com.dulval.stetoskop.resources.utils.URL;
import com.dulval.stetoskop.services.UserService;
import org.apache.catalina.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity read(
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "email", defaultValue = "", required = false) String email,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        
        String nameDecoded = URL.decodeParam(name);
        String emailDecoded = URL.decodeParam(email);
        
        Page<User> list = service.readByCriteria(nameDecoded, emailDecoded, page, linesPerPage, orderBy, direction);
        Page<UserResponse> listDto = list.map(obj -> new UserResponse(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity readById(@PathVariable Integer id) {
        User obj = service.readById(id);
        if (obj instanceof Institution) {
            InstitutionResponse objResponse = new InstitutionResponse((Institution) obj);
            return ResponseEntity.ok().body(objResponse);
        } else if (obj instanceof Doctor) {
            DoctorResponse objResponse = new DoctorResponse((Doctor) obj);
            return ResponseEntity.ok().body(objResponse);
        } else {
            return ResponseEntity.ok().body(obj);
        }
    }
}
