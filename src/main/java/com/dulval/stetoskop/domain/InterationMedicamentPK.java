/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import lombok.Data;

/**
 *
 * @author Diego Dulval
 */
@Data
@Embeddable
public class InterationMedicamentPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "medicament_id")
    private Integer medicament;

    @JoinColumn(name = "medicament_interation_id")
    private Integer medicamentInteration;
}
