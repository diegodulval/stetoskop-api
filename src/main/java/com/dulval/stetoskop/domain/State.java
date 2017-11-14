package com.dulval.stetoskop.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String uf;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<City> citys = new ArrayList<>();

    public State(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public State() {
    }
}
