package com.isaguler.cdcdebezium.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConfiguration {

    private static final String CUSTOMER = "customer";

    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {
        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");

        return io.debezium.config.Configuration.create()
                .with("name", "customer-mongodb-connector")//
                .with("connector.class", "io.debezium.connector.mongodb.MongoDbConnector")//
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", "localhost")
                .with("database.port", "27017")
                .with("database.user", "root")//
                .with("database.password", "password123")//
                .with("database.dbname", CUSTOMER)
                .with("database.include.list", CUSTOMER)
                .with("mongodb.connection.string", "mongodb://root:password123@localhost:27017/")//
                .with("topic.prefix", CUSTOMER)//
                //.with("include.schema.changes", "false")
                /*.with("database.server.id", "10181")
                .with("database.server.name", "customer-mongo-db-server")*/
                /*.with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")*/
                //.with("mongodb.connection.mode", "sharded")//
                .build();
    }

}
