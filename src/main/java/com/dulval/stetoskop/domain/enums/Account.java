package com.dulval.stetoskop.domain.enums;

public enum Account {

    PREMIUM(1, "Premium"),
    FREE(2, "Free");

    private int cod;
    private String description;

    private Account(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static Account toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Account x : Account.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
