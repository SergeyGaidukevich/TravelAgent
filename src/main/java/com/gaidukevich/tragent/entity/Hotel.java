package com.gaidukevich.tragent.entity;

import java.util.Objects;

public class Hotel extends Entity {
    private String name;
    private String phone;
    private Country country;
    private int stars;

    public Hotel() {
    }

    public Hotel(Long id, String name, String phone, Country country, int stars) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return stars == hotel.stars &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(phone, hotel.phone) &&
                Objects.equals(country, hotel.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phone, country, stars);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", stars=" + stars +
                '}';
    }
}
