/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.form;

import com.dulval.stetoskop.domain.Institution;
import java.io.Serializable;

public class InstitutionForm extends UserForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public InstitutionForm(Institution obj) {
        super(obj.getId(), obj.getName(), obj.getEmail(), obj.getPassword(), obj.getPhone());
    }
    
     public Institution build() {
       
        return new Institution(
                null,
                this.getName(),
                this.getEmail(),
                this.getPassword(),
                this.getPhone()
        );
    }

    public InstitutionForm() {
    }

}
