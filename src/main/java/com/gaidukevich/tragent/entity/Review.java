package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    @NotNull
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "content")
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
