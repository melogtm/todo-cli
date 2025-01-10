package com.melogtm.cli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFix {
    // fixes "An error occurred while adding a task: Java 8 date/time type java.time.LocalDateTime
    // not supported by default..."
    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
