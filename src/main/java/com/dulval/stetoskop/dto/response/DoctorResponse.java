/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.response;

import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.domain.enums.Account;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Diego Dulval
 */
@Data
public class DoctorResponse extends UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String profession;
    private String crm;
    private Account account;
    private String phone;
    private String cpf;

    public DoctorResponse(Doctor obj) {
        super(obj.getId(), obj.getName(), obj.getEmail(), obj.getPassword(), obj.getAddress());
        this.profession = obj.getProfession();
        this.crm = obj.getCrm();
        this.account = Account.toEnum(obj.getAccount());
        this.phone = obj.getPhone();
        this.cpf = obj.getCpf();
    }

    public DoctorResponse() {
    }
}
