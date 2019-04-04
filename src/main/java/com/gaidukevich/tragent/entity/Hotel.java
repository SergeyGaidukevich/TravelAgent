package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hotels")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Hotel_getById", query = "SELECT hotel FROM Hotel hotel WHERE hotel.id = :hotel_id")
public class Hotel extends SuperEntity {

    @Column(name = "hotel_name")
    @NotBlank(message = "Please entry name")
    private String name;

    @Column(name = "phone")
    @NotBlank(message = "Please entry phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @MapsId
    @NotNull(message = "Please entry country")
    private Country country;

    @Column(name = "stars")
    @Digits(integer = 1, fraction = 0, message = "one digit")
    @NotNull(message = "Please entry stars")
    @Min(0)
    @Max(5)
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
}
