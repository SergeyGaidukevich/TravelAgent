package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Repository("userRepository")
@Profile("jdbc")
public class JdbcTemplateUserRepository implements EntityRepository<User> {

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

    @Autowired
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
        return Optional.ofNullable(jdbcTemplate.query(SQL_SELECT_USER_BY_ID, new UserMapExtractor(), id.intValue()))
                .map(List::stream)
                .flatMap(Stream::findFirst)
                .orElse(null);
    }

    private void putUserParametersInMap(Map<String, Object> params, User user) {
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    private static final class UserMapExtractor implements ResultSetExtractor<List<User>> {

        @Override
        public List<User> extractData(ResultSet rs) throws SQLException {
            Map<Long, User> users = new HashMap<>();
            Map<Long, Set<Tour>> userTourMap = new HashMap<>();
            Map<Long, Set<Review>> userReviewMap = new HashMap<>();

            while (rs.next()) {
                long userId = rs.getLong("user_id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                long tourId = rs.getLong("tour_id");
                long reviewId = rs.getLong("review_id");

                users.putIfAbsent(userId, new User(userId, login, password));

                Set<Tour> tours = Optional.ofNullable(userTourMap.get(userId)).orElseGet(HashSet::new);
                tours.add(new Tour(tourId));
                userTourMap.putIfAbsent(userId, tours);

                Set<Review> reviews = Optional.ofNullable(userReviewMap.get(userId)).orElseGet(HashSet::new);
                reviews.add(new Review(reviewId));
                userReviewMap.putIfAbsent(userId, reviews);
            }

            users.forEach((userId, user) -> {
                user.setReviews(new ArrayList<>(userReviewMap.get(userId)));
                user.setTours(new ArrayList<>(userTourMap.get(userId)));
            });

            return new ArrayList<>(users.values());
        }
    }
}