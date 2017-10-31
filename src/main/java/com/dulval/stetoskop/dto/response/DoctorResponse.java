/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.response;

import com.dulval.stetoskop.domain.Address;
import com.dulval.stetoskop.domain.Doctor;
import com.dulval.stetoskop.domain.Institution;
import com.dulval.stetoskop.domain.User;
import com.dulval.stetoskop.domain.enums.Account;
import java.io.Serializable;

/**
 *
 * @author diego-dulval
 */
public class DoctorResponse extends UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String profession;
    private String crm;
    private Account account;
    private String phone;

    public DoctorResponse(Doctor obj) {
        super(obj.getId(), obj.getName(), obj.getEmail(), obj.getPassword(), obj.getAddress());
        this.profession = obj.getProfession();
        this.crm = obj.getCrm();
        this.account = Account.toEnum(obj.getAccount());
        this.phone = obj.getPhone();
    }

    public DoctorResponse() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
