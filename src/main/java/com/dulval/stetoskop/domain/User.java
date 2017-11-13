package com.dulval.stetoskop.domain;

import com.dulval.stetoskop.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Collectors;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "sys_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROLES")
    private Set<Integer> roles = new HashSet<>();

    public User() {
       
    }

    public User(Integer id, String name, String email, String password, Address address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
    }

    public void addRoles(Role perfil) {
        roles.add(perfil.getCod());
    }
}
