package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tours")
@Data
@EqualsAndHashCode(callSuper = false)
@NamedQuery(name = "User_getById", query = "SELECT user FROM User user WHERE user.id = :user_id")
public class User extends SuperEntity {

    @Column(name = "login")
    @NotBlank(message = "Please entry login")
    @Size(max = 45, message = "Invalid login")
    private String login;

    @Column(name = "password")
    @NotBlank(message = "Please entry password")
    @Size(max = 45, message = "Invalid password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_tours",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    private List<Tour> tours;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews;

    public User() {
    }

    public User(Long id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password, List<Tour> tours, List<Review> reviews) {
        super(id);
        this.login = login;
        this.password = password;
        this.tours = tours;
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", tours=" + tours +
                '}';
    }
}