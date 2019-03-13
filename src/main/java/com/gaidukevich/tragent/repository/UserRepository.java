package com.gaidukevich.tragent.repository;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;

import java.util.List;

public interface UserRepository extends EntityRepository<User> {
    void updateLogin(Long id, String login);

    void updatePassword(Long id, String password);

    void updateTours(Long id, List<Tour> tours);

    void updateReview(Long id, List<Tour> reviews);
}
