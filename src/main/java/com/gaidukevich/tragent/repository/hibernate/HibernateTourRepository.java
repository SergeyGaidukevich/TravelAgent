package com.gaidukevich.tragent.repository.hibernate;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("tourRepository")
@Profile("hibernate")
public class HibernateTourRepository implements EntityRepository<Tour> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(Tour entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void update(Long id, Tour entity) {

    }

    @Override
    @Transactional
    public List<Tour> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Tour getById(Long id) {
        List tours = this.entityManager.createNamedQuery("Tour_getById")
                .setParameter("tour_id", id)
                .getResultList();

        return (Tour) tours.get(0);
    }
}
