package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public class SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Please entry id")
    private Long id;

    SuperEntity() {
    }

    SuperEntity(Long id) {
        this.id = id;
    }
}
