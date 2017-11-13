package com.dulval.stetoskop.domain;

import com.dulval.stetoskop.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Doctor extends User {

    private static final long serialVersionUID = 1L;

    private String profession;
    private String crm;
    private Integer account;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor")
    private List<Pacient> pacients = new ArrayList<>();

    public Doctor(String profession, String crm, Integer account) {
        this.profession = profession;
        this.crm = crm;
        this.account = account;
        addRoles(Role.CLIENTE);
    }

    public Doctor(String profession, String crm, Integer account, Integer id, String name, String email, String password, Address address, String phone) {
        super(id, name, email, password, address, phone);
        this.profession = profession;
        this.crm = crm;
        this.account = account;
        addRoles(Role.CLIENTE);
    }

    public Doctor() {
        addRoles(Role.CLIENTE);
    }

}
