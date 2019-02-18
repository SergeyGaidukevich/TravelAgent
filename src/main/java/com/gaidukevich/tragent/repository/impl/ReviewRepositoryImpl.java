package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.ReviewRepository;

public class ReviewRepositoryImpl extends GenericRepository<Review> implements ReviewRepository {
    @Override
    public void update(Long id, Review newReview) {
        Review review = getById(id);

        review.setTour(newReview.getTour());
        review.setUser(newReview.getUser());
        review.setContent(newReview.getContent());
    }

    public void updateTour(Long id, Tour tour) {
        getById(id).setTour(tour);
    }

    @Override
    public void updateUser(Long id, User user) {
        getById(id).setUser(user);
    }

    @Override
    public void updateContent(Long id, String content) {
        getById(id).setContent(content);
    }
}
