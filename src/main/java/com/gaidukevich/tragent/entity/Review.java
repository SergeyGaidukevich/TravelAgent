package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class Review extends Entity {
    @NotNull
    private Tour tour;

    @NotNull
    private User user;

    @NotBlank(message = "Please enter content")
    private String content;

    public Review() {
    }

    public Review(Long id) {
        super(id);
    }

    public Review(Long id, Tour tour, User user, String content) {
        super(id);
        this.tour = tour;
        this.user = user;
        this.content = content;
    }
}
