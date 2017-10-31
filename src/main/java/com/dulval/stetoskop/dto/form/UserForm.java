/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.form;

import java.io.Serializable;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 1, max = 40, message = "O tamanho deve ser entre 1 e 40 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 1, max = 80, message = "O tamanho deve ser entre 1 e 80 caracteres")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 6, max = 20, message = "O tamanho deve ser entre 6 e 20 caracteres")
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String phone;

    public UserForm(Integer id, String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public UserForm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
