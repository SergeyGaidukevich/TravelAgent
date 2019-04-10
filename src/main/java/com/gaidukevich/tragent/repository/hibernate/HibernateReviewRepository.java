package com.gaidukevich.tragent.repository.hibernate;

import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("reviewRepository")
@Profile("hibernate")
public class HibernateReviewRepository implements EntityRepository<Review> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(Review entity) {

    }

    @Override
    @Transactional
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void update(Long id, Review entity) {

    }

    @Override
    @Transactional
    public List<Review> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Review getById(Long id) {
        List reviews = this.entityManager.createNamedQuery("Review_getById")
                .setParameter("review_id", id)
                .getResultList();

        return (Review) reviews.get(0);
    }
}
