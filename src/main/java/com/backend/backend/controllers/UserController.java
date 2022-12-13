package com.backend.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.backend.interfaces.GlobalController;
import com.backend.backend.interfaces.IRepository;
import com.backend.backend.models.Customer;
import com.backend.backend.services.*;

@RestController
@RequestMapping(path = "api/customer")
public class UserController extends GlobalController<Customer> {

    private final UserService service;

    @Autowired
    public UserController(final IRepository<Customer> repository, final UserService service) {
        super(repository);
        this.service = service;
    }

    @RequestMapping
    public ResponseEntity<List<Customer>> getUser() {
        return ResponseEntity.ok(getResponse());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Customer> getSingleUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(getSingleResponse(id));
    }

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        Optional<Customer> result = service.findUserByEmail(customer.getEmail());
        if (result.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        return ResponseEntity.ok(postRequest(customer));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id,
            @RequestBody Customer customer) {
        return (ResponseEntity<Customer>) ResponseEntity.ok(putRequest(customer,
                id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean result = deleteRequest(id);
        if (!result) {
            throw new IllegalStateException("User does not exist");
        }
        return ResponseEntity.ok("Deleted Successfully");
    }
}
