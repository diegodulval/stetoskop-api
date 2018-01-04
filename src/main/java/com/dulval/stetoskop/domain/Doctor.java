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
    private String cpf;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor")
    private List<Pacient> pacients = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "doctor")
    private List<Posology> posologys = new ArrayList<>();

    public Doctor(String profession, String crm, Integer account, Integer id, String name, String email, String password, Address address, String phone, String cpf) {
        super(id, name, email, password, address, phone, Role.DOCTOR.getCod());
        this.profession = profession;
        this.crm = crm;
        this.account = account;
        this.cpf = cpf;
    }

    public Doctor() {
        this.role = Role.DOCTOR.getCod();
    }

}
