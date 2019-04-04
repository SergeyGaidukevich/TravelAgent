package com.gaidukevich.tragent.repository.hibernate;

import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("hotelRepository")
@Profile("hibernate")
public class HibernateHotelRepository implements EntityRepository<Hotel> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(Hotel entity) {

    }

    @Override
    @Transactional
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void update(Long id, Hotel entity) {

    }

    @Override
    @Transactional
    public List<Hotel> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Hotel getById(Long id) {
        List hotels = this.entityManager.createNamedQuery("Hotel_getById")
                .setParameter("hotel_id", id)
                .getResultList();

        return (Hotel) hotels.get(0);
    }
}
