package com.gaidukevich.tragent.repository.jdbc;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.tour_type.TourType;
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

@Repository("tourRepository")
@Profile("jdbc")
public class JdbcTemplateTourRepository implements EntityRepository<Tour> {
    private static final String SQL_INSERT_TOUR = "INSERT INTO tours (photo, country_id, date, duration, , hotel_id," +
            " type, description, cost) VALUES (:photo, :country_id, :date, :duration, :hotel_id, :type, :description, :cost)";
    private static final String SQL_DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE tour_id = ?";
    private static final String SQL_UPDATE_TOUR_BY_ID = "UPDATE tours SET photo = :photo, country_id = :country_id," +
            " date = :date, duration = :duration, hotel_id = :hotel_id, type = :type, description = :description," +
            " cost = :cost WHERE tour_id = :tour_id";
    private static final String SQL_SELECT_ALL_TOURS = "SELECT * FROM tours";
    private static final String SQL_SELECT_TOUR_BY_ID = "SELECT * FROM tours LEFT JOIN countries" +
            " ON tours.country_id = countries.country_id WHERE tour_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateTourRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(Tour tour) {
        Map<String, Object> params = new HashMap<>();
        putTourParametersInMap(params, tour);

        getNamedParameterJdbcTemplate().update(SQL_INSERT_TOUR, params);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        jdbcTemplate.update(SQL_DELETE_TOUR_BY_ID, id.intValue());
    }

    @Override
    @Transactional
    public void update(Long id, Tour tour) {
        Map<String, Object> params = new HashMap<>();
        params.put("tour_id", tour.getId());
        putTourParametersInMap(params, tour);

        getNamedParameterJdbcTemplate().update(SQL_UPDATE_TOUR_BY_ID, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_TOURS, this::mapTour);
    }

    @Override
    @Transactional(readOnly = true)
    public Tour getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_TOUR_BY_ID, this::mapTour, id.intValue());
    }

    private void putTourParametersInMap(Map<String, Object> params, Tour tour) {
        params.put("photo", tour.getPhoto());
        params.put("date", tour.getDate());
        params.put("duration", tour.getDuration());
        params.put("country_id", tour.getCountry().getId());
        params.put("hotel_id", tour.getHotel().getId());
        params.put("type", tour.getType());
        params.put("description", tour.getDescription());
        params.put("cost", tour.getCost());
    }

    private Tour mapTour(ResultSet rs, int rowNum) throws SQLException {
        Tour tour = new Tour();
        tour.setPhoto(rs.getString("photo"));
        tour.setDate(rs.getString("date"));
        tour.setDuration(rs.getString("duration"));

        Long country_id = rs.getLong("country_id");
        String country_name = rs.getString("country_name");
        tour.setCountry(new Country(country_id, country_name));

        tour.setHotel(new Hotel(rs.getLong("hotel_id"), null, null, null, 0));
        tour.setType(TourType.valueOf(rs.getString("type").toUpperCase()));
        tour.setDescription(rs.getString("description"));
        tour.setCost(rs.getDouble("cost"));

        return tour;
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
