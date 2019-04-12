package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tours")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Tour_getById", query = "SELECT tour FROM Tour tour WHERE tour.id = :tour_id")
public class Tour extends SuperEntity {

    @Column(name = "photo")
    @NotBlank(message = "Please enter photo")
    private String photo;

    @Column(name = "date")
    @NotBlank(message = "Please enter date")
    private String date;

    @Column(name = "duration")
    @NotBlank(message = "Please enter duration")
    private String duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @NotNull
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    @NotNull
    private Hotel hotel;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TourType type;

    @Column(name = "description")
    @NotBlank(message = "Please enter description")
    private String description;

    @Column(name = "cost")
    @NotNull(message = "Please enter cost")
    @Min(0)
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
}
