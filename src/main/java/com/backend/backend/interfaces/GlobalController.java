package com.backend.backend.interfaces;

public abstract non-sealed class GlobalController<T> extends GlobalService<T> {

    public GlobalController(IRepository<T> repository) {
        super(repository);
    }

}
