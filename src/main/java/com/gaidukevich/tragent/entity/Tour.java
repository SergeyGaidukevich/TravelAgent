package com.gaidukevich.tragent.entity;

import java.util.Objects;

public class Tour extends Entity {
    private String photo;
    private String date;
    private String duration;
    private Country country;
    private Hotel hotel;
    private TourType type;
    private String description;
    private Double cost;

    public Tour() {
    }

    public Tour(Long id) {
        super(id);
    }

    public Tour(Long id, String photo, String date, String duration, Country country, Hotel hotel, TourType type,
                String description, Double cost) {
        super(id);
        this.photo = photo;
        this.date = date;
        this.duration = duration;
        this.country = country;
        this.hotel = hotel;
        this.type = type;
        this.description = description;
        this.cost = cost;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return Objects.equals(photo, tour.photo) &&
                Objects.equals(date, tour.date) &&
                Objects.equals(duration, tour.duration) &&
                Objects.equals(country, tour.country) &&
                Objects.equals(hotel, tour.hotel) &&
                Objects.equals(type, tour.type) &&
                Objects.equals(description, tour.description) &&
                Objects.equals(cost, tour.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), photo, date, duration, country, hotel, type, description, cost);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "photo='" + photo + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", country=" + country +
                ", hotel=" + hotel +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
