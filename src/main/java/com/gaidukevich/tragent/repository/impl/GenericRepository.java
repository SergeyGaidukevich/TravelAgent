package com.gaidukevich.tragent.repository.impl;

import com.gaidukevich.tragent.entity.Entity;
import com.gaidukevich.tragent.repository.EntityRepository;
import com.gaidukevich.tragent.repository.exception.EntityAlreadyExistsException;
import com.gaidukevich.tragent.repository.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<T extends Entity> implements EntityRepository<T> {
    private final List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) {
        if (doesOrderExist(entity)) {
            throw new EntityAlreadyExistsException("Such entity already exists.");
        }

        entities.add(entity);
    }

    @Override
    public void remove(Long id) {
        entities.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public List<T> getAll() {
        return entities;
    }

    @Override
    public T getById(Long id) {
        return entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("This Entity not found!"));
    }

    private boolean doesOrderExist(T entity) {
        return entities.stream().anyMatch(entity::equals);
    }
}
