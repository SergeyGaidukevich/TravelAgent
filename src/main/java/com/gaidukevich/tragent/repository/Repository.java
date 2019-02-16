package com.gaidukevich.tragent.repository;

import java.util.List;

public interface Repository<T> {
    void add(T instance);

    void remove(Long id);

    void update(Long id, T entity);

    List<T> getAll();

    T getById(Long id);
}