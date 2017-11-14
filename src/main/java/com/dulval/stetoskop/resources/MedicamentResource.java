/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.resources;

import com.dulval.stetoskop.domain.Medicament;
import com.dulval.stetoskop.dto.form.MedicamentForm;
import com.dulval.stetoskop.dto.response.MedicamentResponse;
import com.dulval.stetoskop.resources.utils.URL;
import com.dulval.stetoskop.services.MedicamentService;
import java.net.URI;
import javax.validation.Valid;
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
@RequestMapping(value = "/medicaments")
public class MedicamentResource {

    @Autowired
    private MedicamentService service;

    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Integer id) {
        Medicament obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody MedicamentForm obj) {
        Medicament med = service.create(obj.build());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(med.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody MedicamentForm obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj.build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity read(
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nameDecoded = URL.decodeParam(name);
        Page<Medicament> list = service.readByCriteria(nameDecoded, page, linesPerPage, orderBy, direction);
        Page<MedicamentResponse> listDto = list.map(MedicamentResponse::new);
        return ResponseEntity.ok().body(listDto);
    }
}
