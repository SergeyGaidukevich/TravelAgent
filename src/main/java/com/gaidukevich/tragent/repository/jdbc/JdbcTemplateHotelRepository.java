package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.repository.HotelRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateHotelRepository implements HotelRepository {
    private static final String SQL_INSERT_HOTEL = "INSERT INTO hotels (name, phone, country_id, stars)" +
            " VALUES (:name, :phone, :country_id, :stars)";
    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE hotel_id = ";
    private static final String SQL_UPDATE_HOTEL_BY_ID = "UPDATE hotels SET name = :name, phone = :phone," +
            " country_id = :country, stars = :stars WHERE hotel_id = :id";
    private static final String SQL_SELECT_ALL_HOTELS = "SELECT * from hotels";
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Hotel hotel) {
        Map<String, Object> params = new HashMap<>();
        putSomeParametersInMap(params, hotel);

        jdbcTemplate.update(SQL_INSERT_HOTEL, params);
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_HOTEL_BY_ID, id.toString());
    }

    @Override
    public void update(Long id, Hotel hotel) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        putSomeParametersInMap(params, hotel);

        jdbcTemplate.update(SQL_UPDATE_HOTEL_BY_ID, params);
    }

    @Override
    public void updateName(Long id, String name) {

    }

    @Override
    public void updatePhone(Long id, String phone) {

    }

    @Override
    public void updateCountry(Long id, Country country) {

    }

    @Override
    public void updateStars(Long id, int stars) {

    }

    @Override
    public List<Hotel> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_HOTELS, this::mapHotel);
    }

    @Override
    public Hotel getById(Long id) {
        return null;
    }

    private void putSomeParametersInMap(Map<String, Object> params, Hotel hotel) {
        params.put("name", hotel.getName());
        params.put("phone", hotel.getPhone());
        params.put("country_id", hotel.getCountry());
        params.put("stars", hotel.getStars());
    }

    private Hotel mapHotel(ResultSet rs, int rowNum) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("hotel_id"));
        hotel.setName(rs.getString("name"));
        hotel.setPhone(rs.getString("phone"));
        Country country = new Country();
        country.setId(rs.getLong("country_id"));
        hotel.setCountry(country);
        hotel.setStars(rs.getInt("stars"));

        return hotel;
    }
}
