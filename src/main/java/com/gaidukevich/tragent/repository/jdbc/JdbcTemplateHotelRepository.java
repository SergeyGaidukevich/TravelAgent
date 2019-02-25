package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateHotelRepository implements Repository<Hotel> {
    private static final String SQL_INSERT_HOTEL = "INSERT INTO hotels (name, phone, country_id, stars)" +
            " VALUES (:hotel_name, :phone, :country_id, :stars)";
    private static final String SQL_SELECT_HOTEL_BY_ID = "SELECT * FROM hotels LEFT JOIN countries" +
            " ON hotels.country_id = countries.country_id WHERE hotels_id = ?";
    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE hotel_id = ?";
    private static final String SQL_UPDATE_HOTEL_BY_ID = "UPDATE hotels SET name = :hotel_name, phone = :phone," +
            " country_id = :country_id, stars = :stars WHERE hotel_id = :hotel_id";
    private static final String SQL_SELECT_ALL_HOTELS = "SELECT * FROM hotels LEFT JOIN countries" +
            " ON hotels.country_id = countries.country_id";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateHotelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Hotel hotel) {
        Map<String, Object> params = new HashMap<>();
        putHotelParametersInMap(params, hotel);

        jdbcTemplate.update(SQL_INSERT_HOTEL, params);
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_HOTEL_BY_ID, id.intValue());
    }

    @Override
    public void update(Long id, Hotel hotel) {
        Map<String, Object> params = new HashMap<>();
        params.put("hotel_id", id);
        putHotelParametersInMap(params, hotel);

        jdbcTemplate.update(SQL_UPDATE_HOTEL_BY_ID, params);
    }

    @Override
    public List<Hotel> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HOTELS, this::mapHotel);
    }

    @Override
    public Hotel getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_HOTEL_BY_ID, this::mapHotel, id.intValue());
    }

    private void putHotelParametersInMap(Map<String, Object> params, Hotel hotel) {
        params.put("hotel_name", hotel.getName());
        params.put("phone", hotel.getPhone());
        params.put("country_id", hotel.getCountry().getId());
        params.put("stars", hotel.getStars());
    }

    private Hotel mapHotel(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("hotel_id"));
        hotel.setName(rs.getString("hotel_name"));
        hotel.setPhone(rs.getString("phone"));

        Long country_id = rs.getLong("country_id");
        String country_name = rs.getString("country_name");
        hotel.setCountry(new Country(country_id, country_name));

        hotel.setStars(rs.getInt("stars"));

        return hotel;
    }
}
