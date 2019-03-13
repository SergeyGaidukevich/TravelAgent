package com.gaidukevich.tragent.repository;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;

public interface HotelRepository extends EntityRepository<Hotel> {
    void updateName(Long id, String name);

    void updatePhone(Long id, String phone);

    void updateCountry(Long id, Country country);

    void updateStars(Long id, int stars);
}
