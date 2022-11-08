package com.swe.todoconsoleapp.repository;

import java.util.List;

public interface Repository<T> {
    boolean create(T entity);

    T update(T entity);

    boolean delete(Integer id);

    List<T> get();

    T getById(Integer id);
}
