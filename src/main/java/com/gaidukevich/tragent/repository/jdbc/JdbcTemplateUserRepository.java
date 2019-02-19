package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTemplateUserRepository implements UserRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public void add(User user) {
//        String sqlAddUser = "INSERT INTO travel_agent.users (login, password) VALUES (?, ?);";
//        jdbcTemplate.update(sqlAddUser, user.getLogin(), user.getPassword());
//
//        List<Review> reviews = user.getReviews();
//        String sqlAddReviews = "INSERT INTO travel_agent.reviews (tour_id, user_id, content)" +
//                " VALUES (?, ?, ?);";
//        reviews.forEach(review -> jdbcTemplate.update(sqlAddReviews, review.getTour(), review.getUser(),
//                review.getContent()));
//
//
//        List<Tour> tours = user.getTours();
//        String sqlAddTours = "INSERT INTO travel_agent.tours (photo, date, duration, country_id, hotel_id, type, description, cost)" +
//                " VALUES ('?', '?', '?', '?', '?', '?', '?', '?');";
//        tours.forEach(tour -> jdbcTemplate.update(sqlAddTours, tour.getPhoto(), tour.getDate(), tour.getDuration(),
//                 tour.getCountry(), tour.getHotel(), tour.getType(), tour.getDescription(), tour.getCost()));
//
//        String sqlAddUserTours = "INSERT INTO travel_agent.user_tours (tour_id, user_id) " +
//                " VALUES (?, ?);";
//        tours.forEach(tour -> jdbcTemplate.update(sqlAddUserTours));


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