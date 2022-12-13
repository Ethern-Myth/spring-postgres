package com.backend.backend.interfaces;

import org.springframework.stereotype.Service;

@Service
public sealed interface IHelperService<T> extends IService<T> permits GlobalService<T> {

}
