package com.dulval.stetoskop.domain;

import javax.persistence.Entity;

@Entity
public class Doctor extends User {

    private static final long serialVersionUID = 1L;

    private String profession;
    private String crm;
    private Integer account;

    public Doctor() {
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

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

}
