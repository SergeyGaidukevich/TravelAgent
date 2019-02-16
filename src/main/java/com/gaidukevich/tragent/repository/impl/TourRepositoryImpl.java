package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.TourType;
import com.gaidukevich.tragent.repository.TourRepository;

public class TourRepositoryImpl extends GenericRepository<Tour> implements TourRepository {
    @Override
    public void update(Long id, Tour newTour) {
        Tour tour = getById(id);

        tour.setPhoto(newTour.getPhoto());
        tour.setDate(newTour.getDate());
        tour.setDuration(newTour.getDuration());
        tour.setCountry(newTour.getCountry());
        tour.setHotel(newTour.getHotel());
        tour.setType(newTour.getType());
        tour.setDescription(newTour.getDescription());
        tour.setCost(newTour.getCost());
    }

    @Override
    public void updatePhoto(Long id, String photo) {
        getById(id).setPhoto(photo);
    }

    @Override
    public void updateDate(Long id, String duration) {
        getById(id).setDuration(duration);
    }

    @Override
    public void updateDuration(Long id, String duration) {
        getById(id).setDuration(duration);
    }

    @Override
    public void updateCountry(Long id, Country country) {
        getById(id).setCountry(country);
    }

    @Override
    public void updateHotel(Long id, Hotel hotel) {
        getById(id).setHotel(hotel);
    }

    @Override
    public void updateType(Long id, TourType type) {
        getById(id).setType(type);
    }

    @Override
    public void updateDescription(Long id, String description) {
        getById(id).setDescription(description);
    }

    @Override
    public void updateCost(Long id, Double cost) {
        getById(id).setCost(cost);
    }
}
