package com.gaidukevich.tragent.repository.hibernate;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("countryRepository")
@Profile("hibernate")
public class HibernateCountryRepository implements EntityRepository<Country> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(Country country) {
        this.entityManager.persist(country);
    }


    @Override
    @Transactional
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void update(Long id, Country entity) {

    }

    @Override
    @Transactional
    public List<Country> getAll() {
        return null;
    }

    @Override
    @Transactional
    public Country getById(Long id) {
        return null;
    }
}
