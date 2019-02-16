package com.gaidukevich.tragent.repository;

import com.gaidukevich.tragent.entity.Country;

public interface CountryRepository extends Repository<Country> {
    void updateName(Long id, String name);
}
