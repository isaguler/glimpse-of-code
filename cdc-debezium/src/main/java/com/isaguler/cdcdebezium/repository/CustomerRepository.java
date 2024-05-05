package com.isaguler.cdcdebezium.repository;

import com.isaguler.cdcdebezium.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(String email);
}
