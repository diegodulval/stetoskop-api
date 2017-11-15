/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.form;

import com.dulval.stetoskop.domain.Address;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 1, max = 40, message = "O tamanho deve ser entre 1 e 40 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 1, max = 80, message = "O tamanho deve ser entre 1 e 80 caracteres")
    private String email;

    @Length(min = 6, max = 20, message = "O tamanho deve ser entre 6 e 20 caracteres")
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String phone;

    private Address address;

    public UserForm(Integer id, String name, String email, String password, Address address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public UserForm() {
    }
}
