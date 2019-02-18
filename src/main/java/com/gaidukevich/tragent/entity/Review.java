package com.gaidukevich.tragent.entity;

import java.util.List;
import java.util.Objects;

public class Review extends Entity {
    private Tour tour;
    private User user;
    private String content;

    public Review() {}

    public Review(Long id, Tour tour, User user, String content) {
        super(id);
        this.tour = tour;
        this.user = user;
        this.content = content;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return Objects.equals(tour, review.tour) &&
                Objects.equals(user, review.user) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tour, user, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "tours=" + tour +
                ", user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
