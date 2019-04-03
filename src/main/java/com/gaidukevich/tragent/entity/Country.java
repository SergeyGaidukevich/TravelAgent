package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class Country extends Entity {
    @NotBlank(message = "Please entry name")
    private String name;

    public Country() {
    }

    public Country(Long id, String name) {
        super(id);
        this.name = name;
    }
}
