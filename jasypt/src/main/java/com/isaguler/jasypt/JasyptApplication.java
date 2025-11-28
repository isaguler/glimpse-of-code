package com.isaguler.jasypt;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class JasyptApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasyptApplication.class, args);
	}

}
