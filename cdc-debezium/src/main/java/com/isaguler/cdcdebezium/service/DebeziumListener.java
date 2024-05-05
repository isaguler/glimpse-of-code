package com.isaguler.cdcdebezium.service;

import com.isaguler.cdcdebezium.model.Customer;
import com.isaguler.cdcdebezium.utils.HandlerUtils;
import io.debezium.config.Configuration;
import io.debezium.data.Envelope;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class DebeziumListener {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final CustomerService customerService;
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    public DebeziumListener(Configuration customerConnectorConfiguration, CustomerService customerService) {
        this.customerService = customerService;
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(customerConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();
        log.info("Key = " + sourceRecord.key() + " value = " + sourceRecord.value());

        Struct sourceRecordChangeValue = (Struct) sourceRecord.value();

        if (sourceRecordChangeValue != null) {
            try {
                String operation = HandlerUtils.getOperation(sourceRecordChangeValue);
                String documentId = HandlerUtils.getDocumentId( (Struct) sourceRecord.key());
                String collection = HandlerUtils.getCollection(sourceRecordChangeValue);
                Customer customer = HandlerUtils.getData(sourceRecordChangeValue);

                log.info("Collection : {} , DocumentId : {} , Operation : {}", collection, documentId, operation);
                log.info("Customer : " + customer);

                customerService.replicateData(customer, Envelope.Operation.forCode(operation));

            } catch (Exception e) {
                log.error("handleChangeEvent exception : " + e.getMessage());
            }
        }
    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }
}
