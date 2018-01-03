/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dulval.stetoskop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

/**
 *
 * @author Diego Dulval
 */
@Data
@Entity
public class Pacient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String profession;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "pacient")
    private List<Prescription> prescriptions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties(value = {"pacients", "posologys"})
    private Doctor doctor;
}
