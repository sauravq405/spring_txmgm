package com.demo.tx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonPrettyPrinter {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    static {
        SimpleModule module = new SimpleModule();
        module.addSerializer(ArrayNode.class, new StackTraceSerializer());
        mapper.registerModule(module);
    }

    public static String prettifyJsonForLogging(String jsonString) throws JsonProcessingException {
        // Parse JSON string into JsonNode
        String prettyPrintedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(jsonString));

        // Log the pretty printed JSON
        return prettyPrintedJson;
    }
}
