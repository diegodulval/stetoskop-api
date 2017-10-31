package com.dulval.stetoskop.resources;

import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.dto.response.DoctorResponse;
import com.dulval.stetoskop.resources.utils.URL;
import com.dulval.stetoskop.services.DoctorService;
import com.dulval.stetoskop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorResource {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService service;

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

        Page<Doctor> list = service.read(nameDecoded, emailDecoded, page, linesPerPage, orderBy, direction);
        Page<DoctorResponse> listDto = list.map(obj -> new DoctorResponse(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity readById(@PathVariable Integer id) {
        Doctor obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }
}
