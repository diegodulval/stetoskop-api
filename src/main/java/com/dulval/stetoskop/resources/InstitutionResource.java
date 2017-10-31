package com.dulval.stetoskop.resources;

import com.dulval.stetoskop.domain.Institution;
import com.dulval.stetoskop.dto.form.InstitutionForm;
import com.dulval.stetoskop.dto.response.InstitutionResponse;
import com.dulval.stetoskop.resources.utils.URL;
import com.dulval.stetoskop.services.InstitutionService;
import com.dulval.stetoskop.services.UserService;
import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/institutions")
public class InstitutionResource {

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService service;

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

        Page<Institution> list = service.read(nameDecoded, emailDecoded, page, linesPerPage, orderBy, direction);
        Page<InstitutionResponse> listDto = list.map(obj -> new InstitutionResponse(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity readById(@PathVariable Integer id) {
        Institution obj = service.readById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InstitutionForm objDto) {

        Institution obj = objDto.build();
        obj = (Institution) userService.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
