/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.resources;

import com.dulval.stetoskop.domain.Prescription;
import com.dulval.stetoskop.dto.response.MedicamentResponse;
import com.dulval.stetoskop.dto.response.PrescriptionResponse;
import com.dulval.stetoskop.services.PrescriptionService;
import java.net.URI;
import java.util.Date;
import javax.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Diego Dulval
 */
@RestController
@RequestMapping(value = "/prescriptions")
public class PrescriptionResource {

    @Autowired
    private PrescriptionService service;

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Integer id) {
        Prescription obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody Prescription obj) {
        Prescription med = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody Prescription obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity read(
            @RequestParam(value = "data", defaultValue = "", required = false) Date date,
            @RequestParam(value = "doctor", defaultValue = "0", required = false) Integer doctor,
            @RequestParam(value = "pacient", defaultValue = "0", required = false) Integer pacient,
            
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Prescription> list = service.read(null, page, linesPerPage, orderBy, direction);
        Page<PrescriptionResponse> listDto = list.map(PrescriptionResponse::new);
        return ResponseEntity.ok().body(listDto);
    }
}
