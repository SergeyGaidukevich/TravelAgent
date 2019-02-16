package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class JdbcTemplateUserRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void updateLogin(Long id, String login) {

    }

    @Override
    public void updatePassword(Long id, String password) {

    }

    @Override
    public void updateTours(Long id, List<Tour> tours) {

    }

    @Override
    public void updateReview(Long id, List<Tour> reviews) {

    }

    @Override
    public void add(User instance) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Long id, User entity) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return null;
    }
}