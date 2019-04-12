package com.gaidukevich.tragent.repository;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.entity.Tour;
import com.gaidukevich.tragent.entity.TourType;

public interface TourRepository extends EntityRepository<Tour> {
    void updatePhoto(Long id, String photo);

    void updateDate(Long id, String duration);

    void updateDuration(Long id, String duration);

    void updateCountry(Long id, Country country);

    void updateHotel(Long id, Hotel hotel);

    void updateType(Long id, TourType type);

    void updateDescription(Long id, String description);

    void updateCost(Long id, Double cost);
}
