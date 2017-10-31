/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.response;

import com.dulval.stetoskop.domain.Institution;
import java.io.Serializable;

/**
 *
 * @author diego-dulval
 */
public class InstitutionResponse extends UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public InstitutionResponse(Institution obj) {
        super(obj.getId(), obj.getName(), obj.getEmail(), obj.getPhone(), obj.getAddress());
    }

    public InstitutionResponse() {
    }

}
