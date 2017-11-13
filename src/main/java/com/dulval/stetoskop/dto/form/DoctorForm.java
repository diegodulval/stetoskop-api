/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.form;

import com.dulval.stetoskop.domain.Doctor;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class DoctorForm extends UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String profession;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 1, max = 40, message = "O tamanho deve ser entre 1 e 40 caracteres")
    private String crm;

    private Integer account;

    public Doctor build() {

        return new Doctor(
                this.profession,
                this.crm,
                this.account,
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getPassword(),
                this.getAddress(),
                this.getPhone());
    }

    public DoctorForm() {
    }

}
