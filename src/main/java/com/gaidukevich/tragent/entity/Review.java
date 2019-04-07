package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "Review_getById", query = "SELECT review FROM Review review WHERE review.id = :review_id")
public class Review extends SuperEntity {

    @ManyToOne
    @NotNull
    private Tour tour;

    @ManyToOne
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
