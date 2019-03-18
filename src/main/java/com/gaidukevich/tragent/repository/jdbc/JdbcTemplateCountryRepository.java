package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("countryRepository")
@Profile("jdbc")
public class JdbcTemplateCountryRepository implements EntityRepository<Country> {
    private static final String SQL_SELECT_COUNTRY_BY_ID = "SELECT * FROM countries WHERE country_id = ?";
    private static final String SQL_INSERT_COUNTRY = "INSERT INTO countries (country_name) VALUES (?)";
    private static final String SQL_DELETE_COUNTRY_BY_ID = "DELETE FROM countries WHERE country_id = ?";
    private static final String SQL_SELECT_ALL_COUNTRIES = "SELECT * FROM countries";
    private static final String SQL_UPDATE_COUNTRY_BY_ID = "UPDATE countries SET country_name = :country_name" +
            " WHERE country_id = :country_id";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(Country country) {
        jdbcTemplate.update(SQL_INSERT_COUNTRY, country.getName());
    }

    @Override
    @Transactional
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_COUNTRY_BY_ID, id.intValue());
    }

    @Override
    @Transactional
    public void update(Long id, Country country) {
        Map<String, Object> params = new HashMap<>();
        params.put("country_id", id.intValue());
        params.put("country_name", country.getName());

        getNamedParameterJdbcTemplate().update(SQL_UPDATE_COUNTRY_BY_ID, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COUNTRIES, this::mapCountry);
    }

    @Override
    @Transactional(readOnly = true)
    public Country getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNTRY_BY_ID, this::mapCountry, id.intValue());
    }

    private Country mapCountry(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setId(rs.getLong("country_id"));
        country.setName(rs.getString("country_name"));

        return country;
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
