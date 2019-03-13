package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.entity.Hotel;
import com.gaidukevich.tragent.repository.HotelRepository;

@Deprecated
public class HotelRepositoryImpl extends GenericRepository<Hotel> implements HotelRepository {
    @Override
    public void update(Long id, Hotel newHotel) {
        Hotel hotel = getById(id);

        hotel.setName(newHotel.getName());
        hotel.setPhone(newHotel.getPhone());
        hotel.setCountry(newHotel.getCountry());
        hotel.setStars(newHotel.getStars());
    }

    @Override
    public void updateName(Long id, String name) {
        Hotel hotel = getById(id);

        hotel.setName(name);
    }

    @Override
    public void updatePhone(Long id, String phone) {
        Hotel hotel = getById(id);

        hotel.setPhone(phone);
    }

    @Override
    public void updateCountry(Long id, Country country) {
        Hotel hotel = getById(id);

        hotel.setCountry(country);
    }

    @Override
    public void updateStars(Long id, int stars) {
        Hotel hotel = getById(id);

        hotel.setStars(stars);
    }
}
