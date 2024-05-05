package com.isaguler.cdcdebezium.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaguler.cdcdebezium.model.Customer;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HandlerUtils {

    private HandlerUtils() {
    }

    /**
     * Extracts the document ID from the given Struct object.
     *
     * @param key The Struct object containing the document information.
     * @return The extracted document ID, or null if not found.
     */
    public static String getDocumentId(Struct key) {
        String id = key.getString("id");
        Matcher matcher = Pattern.compile("\"\\$oid\":\\s*\"(\\w+)\"").matcher(id);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Extracts the collection name from the source record value.
     *
     * @param sourceRecordValue The Struct object representing the source record.
     * @return The name of the collection.
     */
    public static String getCollection(Struct sourceRecordValue) {
        Struct source = (Struct) sourceRecordValue.get("source");
        return source.getString("collection");
    }

    /**
     * Deserializes the 'after' field of the source record value into a Product object.
     *
     * @param sourceRecordValue The Struct object representing the source record.
     * @return The deserialized Product object.
     * @throws IOException If there is an error during deserialization.
     */
    public static Customer getData(Struct sourceRecordValue) throws IOException {
        var source = sourceRecordValue.get("after").toString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(source, Customer.class);
    }

    /**
     * Retrieves the operation type from the source record value.
     *
     * @param sourceRecordValue The Struct object representing the source record.
     * @return The operation type, such as "insert", "update", or "delete".
     */
    public static String getOperation(Struct sourceRecordValue) {
        return sourceRecordValue.getString("op");
    }
}
