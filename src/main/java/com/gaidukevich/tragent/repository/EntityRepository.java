package com.gaidukevich.tragent.repository;

import java.util.List;

public interface EntityRepository<T> {
    void add(T entity);

    void remove(Long id);

    void update(Long id, T entity);

    List<T> getAll();

    T getById(Long id);
}