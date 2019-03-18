package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository("userRepository")
@Profile("collectionsRepository")
public class UserRepositoryImpl extends GenericRepository<User> implements UserRepository {
    @Override
    public void update(Long id, User newUser) {
        User user = getById(id);

        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        user.setTours(newUser.getTours());
        user.setReviews(newUser.getReviews());
    }

    @Override
    public void updateLogin(Long id, String login) {
        getById(id).setLogin(login);
    }

    @Override
    public void updatePassword(Long id, String password) {
        getById(id).setPassword(password);
    }

    @Override
    public void updateTours(Long id, List<Tour> tours) {
        getById(id).setTours(tours);
    }

    @Override
    public void updateReview(Long id, List<Tour> reviews) {
        getById(id).setTours(reviews);
    }
}
