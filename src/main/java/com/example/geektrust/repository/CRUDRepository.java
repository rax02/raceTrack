package com.example.geektrust.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository <T, Id> {
    public T save(T entity);
    public List<T> findAll();
    public Optional<T> findById(Id id);
}
