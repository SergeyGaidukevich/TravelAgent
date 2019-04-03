package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public class Entity {

    @Id
    @NotNull(message = "Please entry id")
    private Long id;

    Entity() {
    }

    Entity(Long id) {
        this.id = id;
    }
}
