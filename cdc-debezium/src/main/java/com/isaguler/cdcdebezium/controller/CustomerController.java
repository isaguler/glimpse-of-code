package com.isaguler.cdcdebezium.controller;

import com.isaguler.cdcdebezium.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public void addOrUpdateCustomer(@RequestParam String name, @RequestParam String email) {
        customerService.addOrUpdateCustomer(name, email);
    }
}
