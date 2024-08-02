package com.isaguler.jobrunr_demo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonRepository {

    private Map<Long, String> personList = new HashMap<>();

    @PostConstruct
    void init() {
        personList.put(1L, "First");
        personList.put(2L, "Second");
        personList.put(3L, "Third");


    }

    public int count() {
        return personList.size();
    }
}
