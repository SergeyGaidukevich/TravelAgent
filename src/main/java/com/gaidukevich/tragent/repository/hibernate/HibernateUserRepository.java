package com.gaidukevich.tragent.repository.hibernate;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userRepository")
@Profile("hibernate")
public class HibernateUserRepository implements EntityRepository<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User entity) {

    }

    @Override
    @Transactional
    public void remove(Long id) {

    }

    @Override
    @Transactional
    public void update(Long id, User entity) {

    }

    @Override
    @Transactional
    public List<User> getAll() {
        return null;
    }

    @Override
    @Transactional
    public User getById(Long id) {
        List users = this.entityManager.createNamedQuery("User_getById")
                .setParameter("user_id", id)
                .getResultList();

        return (User) users.get(0);
    }
}
