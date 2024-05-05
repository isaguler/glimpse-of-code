package com.isaguler.cdcdebezium.service;

import com.isaguler.cdcdebezium.model.Customer;
import com.isaguler.cdcdebezium.repository.CustomerRepository;
import io.debezium.data.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void replicateData(Customer customer, Envelope.Operation operation) {
        if (Envelope.Operation.DELETE == operation) {
            log.info("operation is DELETE & customer : " + customer);

        } else {
            log.info("operation is {} & customer : {}", operation, customer);
        }
    }

    public void addOrUpdateCustomer(String name, String email) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(email);

        if (optionalCustomer.isPresent()) {
            optionalCustomer.get().setName(name);
            customerRepository.save(optionalCustomer.get());
        } else {
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customerRepository.save(customer);
        }
    }

}
