package com.isaguler.cdcdebezium.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    private String name;

    private String email;

}
