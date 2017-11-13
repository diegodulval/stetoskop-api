/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.dto.response;

import com.dulval.stetoskop.domain.Pacient;
import com.dulval.stetoskop.domain.Prescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@Data
public class PrescriptionResponse {

    private Integer id;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private Pacient pacient;

    public PrescriptionResponse(Prescription obj) {

        this.id = obj.getId();
        this.description = obj.getDescription();
        this.date = obj.getDate();
        this.pacient = obj.getPacient();

    }
}
