/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Diego Dulval
 */
@Data
@Entity
public class Medicament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "id.medicament")
    private Set<InterationMedicament> interations = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.medicamentInteration")
    private Set<InterationMedicament> medicamentInteration = new HashSet<>();

    @ElementCollection
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "APRESENTATION")
    private Set<String> apresentations = new HashSet<>();

    @ElementCollection
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "COMERCIAL_NAME")
    private Set<String> comercialNames = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.prescription")
    private Set<ItemPrescription> prescription = new HashSet<>();

    public Medicament(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Medicament() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
