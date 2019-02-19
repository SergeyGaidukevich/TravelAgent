package com.gaidukevich.tragent.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTourRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateTourRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
