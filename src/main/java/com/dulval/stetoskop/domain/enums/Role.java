package com.dulval.stetoskop.domain.enums;

public enum Role {
	
	ADMIN(1, "ROLE_ADMIN"),
	DOCTOR(2, "ROLE_DOCTOR");
	
	private int cod;
	private String description;
	
	private Role(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription () {
		return description;
	}
	
	public static Role toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Role x : Role.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
