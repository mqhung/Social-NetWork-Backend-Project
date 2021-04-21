package com.project.socialnetwork.service;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Long id);

    T save(T t);

    void deleteById(Long id);
}
