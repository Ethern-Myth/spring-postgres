package com.backend.backend.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.backend.backend.interfaces.IRepository;
import com.backend.backend.models.Customer;

@Service
public interface UserService extends IRepository<Customer> {

    @Query("SELECT c FROM Customer c WHERE c.email= ?1")
    Optional<Customer> findUserByEmail(String email);
}
