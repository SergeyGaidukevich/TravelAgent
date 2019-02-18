package com.gaidukevich.tragent.entity;

import java.util.Objects;

public class Country extends Entity {
    private String name;

    public Country() {}

    public Country(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
