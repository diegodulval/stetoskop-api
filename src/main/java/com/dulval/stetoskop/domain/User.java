package com.dulval.stetoskop.domain;

import com.dulval.stetoskop.domain.enums.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

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

    //@JsonIgnore
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Address address;

    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ROLES")
    private Set<Integer> roles = new HashSet<>();

    public User(Integer id, String nome, String email, String senha, String phone) {
        super();
        this.id = id;
        this.name = nome;
        this.email = email;
        this.password = senha;
        this.phone = phone;
        addRoles(Role.CLIENTE);
    }

    public User() {
        addRoles(Role.CLIENTE);
    }

    public Set<Role> getRoles() {
        return roles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
    }

    public void addRoles(Role perfil) {
        roles.add(perfil.getCod());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address=" + address + ", roles=" + roles + '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
