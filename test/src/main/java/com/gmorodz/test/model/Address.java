package com.gmorodz.test.model;

import java.util.Objects;

public class Address {
    public Long id;
    public String name;
    public String street;
    public String country_code;

    // Constructores vacíos y con params (para JSON)
    public Address() {}
    
    public Address(Long id, String name, String street, String country_code) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.country_code = country_code;
    }

    // equals/hashCode para comparaciones
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
