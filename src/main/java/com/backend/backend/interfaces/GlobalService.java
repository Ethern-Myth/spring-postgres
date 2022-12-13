package com.backend.backend.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public abstract sealed class GlobalService<T> implements IHelperService<T> permits GlobalController<T> {

    private final IRepository<T> repository;

    @Autowired
    public GlobalService(final IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean deleteRequest(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            return exists;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<T> getResponse() {
        return repository.findAll();
    }

    @Override
    public T getSingleResponse(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("No Data"));
    }

    @Override
    @Transactional
    public T postRequest(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public T putRequest(T t, Long id) {
        T result = repository.findById(id).orElseThrow(() -> new IllegalStateException("No Data to update"));
        if (result != null) {
            return repository.save(t);
        }
        return null;
    }
}
