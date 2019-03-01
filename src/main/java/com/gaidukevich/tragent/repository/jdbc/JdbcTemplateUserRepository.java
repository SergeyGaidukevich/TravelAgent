package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class JdbcTemplateUserRepository implements Repository<User> {
    private static final String SQL_SELECT_ALL_USERS = "SELECT users.user_id, users.login, users.password," +
            " user_tours.tour_id, reviews.review_id FROM users" +
            " LEFT JOIN reviews ON reviews.user_id = users.user_id" +
            " LEFT JOIN user_tours ON users.user_id = user_tours.user_id";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT users.user_id, users.login, users.password," +
            " user_tours.tour_id, reviews.review_id FROM users" +
            " LEFT JOIN reviews ON reviews.user_id = users.user_id" +
            " LEFT JOIN user_tours ON users.user_id = user_tours.user_id  WHERE users.user_id = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO travel_agent.users (login, password)" +
            " VALUES (:login, :password)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET login = :login, password = :password" +
            " WHERE user_id = :user_id";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public void add(User user) {
        Map<String, Object> params = new HashMap<>();
        putUserParametersInMap(params, user);

        getNamedParameterJdbcTemplate().update(SQL_INSERT_USER, params);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, id.intValue());
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user.getId());
        putUserParametersInMap(params, user);

        getNamedParameterJdbcTemplate().update(UPDATE_USER_BY_ID, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapExtractor());
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return Objects.requireNonNull(jdbcTemplate.query(SQL_SELECT_USER_BY_ID, new UserMapExtractor(),
                id.intValue())).get(0);
    }

    private void putUserParametersInMap(Map<String, Object> params, User user) {
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
    }

    private static final class UserMapExtractor implements ResultSetExtractor<List<User>> {
        @Override
        public List<User> extractData(ResultSet rs) throws SQLException {
            Map<Long, User> users = new HashMap<>();
            Map<Long, Set<Tour>> userTourMap = new HashMap<>();
            Map<Long, Set<Review>> userReviewMap = new HashMap<>();

            while (rs.next()) {
                Long user_id = (long) rs.getInt("user_id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                Long tour_id = (long) rs.getInt("tour_id");
                Long review_id = (long) rs.getInt("review_id");

                Set<Tour> tours = userTourMap.get(user_id);
                Tour tour = new Tour();
                tour.setId(tour_id);
                if (tours == null) {
                    tours = new HashSet<>();
                    tours.add(tour);
                    userTourMap.put(user_id, tours);
                } else {
                    tours.add(tour);
                }

                Set<Review> reviews = userReviewMap.get(user_id);
                Review review = new Review();
                review.setId(review_id);
                if (reviews == null) {
                    reviews = new HashSet<>();
                    reviews.add(review);
                    userReviewMap.put(user_id, reviews);
                } else {
                    reviews.add(review);
                }

                User user = users.get(user_id);
                if (user == null) {
                    List<Tour> toursList = new ArrayList<>();
                    List<Review> reviewList = new ArrayList<>();
                    user = new User(user_id, login, password, toursList, reviewList);
                    users.put(user_id, user);
                }
            }

            List<User> extractUsers = new ArrayList<>();
            Set<Long> keys = users.keySet();
            keys.forEach(key -> {
                User user = users.get(key);
                user.setReviews(new ArrayList<>(userReviewMap.get(key)));
                user.setTours(new ArrayList<>(userTourMap.get(key)));
                extractUsers.add(user);
            });

            return extractUsers;
        }
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}