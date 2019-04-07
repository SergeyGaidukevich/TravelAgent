package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "countries")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Country_getById", query = "SELECT country  FROM Country country WHERE country.id = :country_id")
public class Country extends SuperEntity {

    @Column(name = "country_name")
    @NotBlank(message = "Please entry name")
    private String name;

    public Country() {
    }

    public Country(Long id, String name) {
        super(id);
        this.name = name;
    }
}
