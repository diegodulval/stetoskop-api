/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

/**
 *
 * @author Diego Dulval
 */
@Data
@Entity
public class ItemPrescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemPrescriptionPK id = new ItemPrescriptionPK();

    private String description;
    private String apresentation;
    private Integer quantity;

    @ElementCollection
    @CollectionTable(name = "USETYPE")
    private Set<String> useTypes = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "PRESCRIPTION_COMERCIAL_NAME")
    private Set<String> comercialName = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "UNITIE")
    private Set<String> unities = new HashSet<>();

    @JsonIgnoreProperties(value = {"interations"})
    public Medicament getMedicament() {
        return id.getMedicament();
    }

    @JsonIgnore
    public void setPrescription(Prescription obj) {
        id.setPrescription(obj);
    }

    public Prescription getPrescription() {
        return id.getPrescription();
    }

    public void setMedicament(Medicament obj) {
        id.setMedicament(obj);
    }

    public ItemPrescription() {
    }

}
