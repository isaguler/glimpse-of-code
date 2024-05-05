package com.isaguler.cdcdebezium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CdcDebeziumApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcDebeziumApplication.class, args);
	}

}
