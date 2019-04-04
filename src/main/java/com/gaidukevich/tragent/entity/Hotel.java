package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Hotel_getById", query = "FROM Hotel WHERE id = :hotel_id")
public class Hotel extends SuperEntity {
    @NotBlank(message = "Please entry name")
    private String name;

    @NotBlank(message = "Please entry phone")
    private String phone;

    @ManyToOne
    @NotNull(message = "Please entry country")
    private Country country;

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
