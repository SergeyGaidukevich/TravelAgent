package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Country_getById", query = "FROM Country WHERE id = :country_id")
public class Country extends SuperEntity {
    @NotBlank(message = "Please entry name")
    private String name;

    public Country() {
    }

    public Country(Long id, String name) {
        super(id);
        this.name = name;
    }
}
