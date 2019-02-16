package com.gaidukevich.tragent.entity;

import java.util.List;
import java.util.Objects;

public class Review extends Entity {
    private List<Tour> tours;
    private User user;
    private String content;

    public Review(Long id, List<Tour> tours, User user, String content) {
        super(id);
        this.tours = tours;
        this.user = user;
        this.content = content;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
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
        return Objects.equals(tours, review.tours) &&
                Objects.equals(user, review.user) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tours, user, content);
    }

    @Override
    public String toString() {
        return "Review{" +
                "tours=" + tours +
                ", user=" + user +
                ", content='" + content + '\'' +
                '}';
    }
}
