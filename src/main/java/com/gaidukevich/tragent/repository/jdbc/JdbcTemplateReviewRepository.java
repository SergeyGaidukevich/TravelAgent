package com.gaidukevich.tragent.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateReviewRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
