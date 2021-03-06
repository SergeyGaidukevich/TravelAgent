package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Country;
import com.gaidukevich.tragent.repository.CountryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository("countryRepository")
@Profile("collectionsRepository")
public class CountryRepositoryImpl extends GenericRepository<Country> implements CountryRepository {
    @Override
    public void update(Long id, Country newCountry) {
        Country country = getById(id);

        country.setName(newCountry.getName());
    }

    @Override
    public void updateName(Long id, String name) {
        getById(id).setName(name);
    }
}
