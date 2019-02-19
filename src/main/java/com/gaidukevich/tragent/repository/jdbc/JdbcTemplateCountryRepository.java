package com.gaidukevich.tragent.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateCountryRepository {
    private static final String SQL_ELECT_ALL_FROM_COUNTRIES_BY_ID = "SELECT * from countries WHERE country_id = :id";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Country country = jdbcTemplate.queryForObject(SQL_ELECT_ALL_FROM_COUNTRIES_BY_ID, this::mapCountry, country_id);
//
//    private Country mapCountry(ResultSet rs, int rowNum) throws SQLException {
//        Country country = new Country();
//        country.setId(rs.getLong("country_id"));
//        country.setName(rs.getString("name"));
//
//        return country;
//}
}
