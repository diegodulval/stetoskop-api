/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.response;

import com.dulval.stetoskop.domain.Medicament;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class MedicamentResponse {

    private Integer id;
    private String name;
    private Set<String> apresentations = new HashSet<>();
    private Set<String> comercialNames = new HashSet<>();

    public MedicamentResponse() {
    }

    public MedicamentResponse(Medicament obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.apresentations = obj.getApresentations();
        this.comercialNames = obj.getComercialNames();
    }

}
