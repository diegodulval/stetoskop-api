/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

/**
 *
 * @author Diego Dulval
 */
@Data
@Entity
public class InterationMedicament implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private InterationMedicamentPK id = new InterationMedicamentPK();

    private String description;

    @JsonIgnore
    public Medicament getMedicamentInteration() {
        return id.getMedicamentInteration();
    }

    public void setMedicamentInteration(Medicament med) {
        id.setMedicamentInteration(med);
    }

    public Medicament getMedicament() {
        return id.getMedicament();
    }

    public void setMedicament(Medicament med) {
        id.setMedicament(med);
    }
}
