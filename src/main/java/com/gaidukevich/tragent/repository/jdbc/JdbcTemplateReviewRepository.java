package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Review;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.User;
import com.gaidukevich.tragent.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateReviewRepository implements Repository<Review> {
    private static final String SQL_INSERT_REVIEW = "INSERT INTO reviews (tour_id, user_id, content)" +
            " VALUES (:tour_id, :user_id, :content)";
    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE FROM reviews WHERE review_id = ?";
    private static final String SQL_UPDATE_REVIEW_BY_ID = "UPDATE reviews SET name = :tour_id, :user_id, :content" +
            " WHERE review_id = :review_id";
    private static final String SQL_SELECT_ALL_REVIEWS = "SELECT * FROM reviews";
    private static final String SQL_SELECT_ALL_REVIEWS_BY_ID = "SELECT * FROM reviews WHERE reviews_id = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(Review review) {
        Map<String, Object> params = new HashMap<>();
        putReviewParametersInMap(params, review);

        getNamedParameterJdbcTemplate().update(SQL_INSERT_REVIEW, params);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_HOTEL_BY_ID, id.intValue());
    }

    @Override
    @Transactional
    public void update(Long id, Review review) {
        Map<String, Object> params = new HashMap<>();

        params.put("review_id", review.getId());
        putReviewParametersInMap(params, review);

        getNamedParameterJdbcTemplate().update(SQL_UPDATE_REVIEW_BY_ID, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_REVIEWS, this::mapReview);
    }

    @Override
    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_REVIEWS_BY_ID, this::mapReview, id.intValue());
    }

    private void putReviewParametersInMap(Map<String, Object> params, Review review) {
        params.put("tour_id", review.getTour().getId());
        params.put("user_id", review.getUser().getId());
        params.put("content", review.getContent());
    }

    private Review mapReview(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setTour(new Tour(rs.getLong("tour_id"), null, null, null, null,
                null, null, null, null));
        review.setUser(new User(rs.getLong("user_id"), null, null, null, null));
        review.setContent(rs.getString("content"));

        return review;
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
