package com.gaidukevich.tragent.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Entity {
    @NotBlank(message = "Please entry login")
    @Size(max = 45, message = "Invalid login")
    private String login;

    @NotBlank(message = "Please entry password")
    @Size(max = 45, message = "Invalid password")
    private String password;

    private List<Tour> tours;

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
}
