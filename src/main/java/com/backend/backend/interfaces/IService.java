package com.backend.backend.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public sealed interface IService<T> permits IHelperService<T> {
    public List<T> getResponse();

    public T getSingleResponse(Long id);

    public T postRequest(T t);

    public T putRequest(T t, Long id);

    public boolean deleteRequest(Long id);
}
