package com.dulval.stetoskop.domain;

import com.dulval.stetoskop.domain.enums.Role;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Institution extends User {

    private static final long serialVersionUID = 1L;

    public Institution() {
        this.role = Role.ADMIN.getCod();
    }

    public Institution(Integer id, String name, String email, String password, Address address, String phone) {
        super(id, name, email, password, address, phone, Role.ADMIN.getCod());
    }

}
