package com.gaidukevich.tragent.entity;


import java.util.Objects;

public class TourType extends Entity {
    private String name;

    public TourType(Long id, String name) {
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
        TourType tourType = (TourType) o;
        return Objects.equals(name, tourType.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TourType{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
