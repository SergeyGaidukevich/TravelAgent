package com.gaidukevich.tragent.repository;

import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;

import java.util.List;

public interface ReviewRepository extends Repository<Review> {
    void updateTour(Long id, Tour tour);

    void updateUser(Long id, User user);

    void updateContent(Long id, String content);
}
