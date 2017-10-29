package com.dulval.stetoskop.domain;


import javax.persistence.Entity;


@Entity
public class Institution extends User {
	private static final long serialVersionUID = 1L;

	public Institution() {
	}

	public Institution(Integer id, String nome, String email, String senha, String phone ) {
		super(id, nome, email, senha, phone);
	}	
        
        
}
