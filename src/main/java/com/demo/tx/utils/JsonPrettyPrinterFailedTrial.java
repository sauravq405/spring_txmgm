package com.demo.tx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonPrettyPrinterFailedTrial {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static String prettifyJsonForLogging(String jsonString) throws JsonProcessingException {
        // Parse JSON string into JsonNode
        ObjectNode rootNode = (ObjectNode) mapper.readTree(jsonString);
        ObjectNode errorResponse = (ObjectNode) rootNode.get("errorResponse");

        if (errorResponse != null) {
            ArrayNode stackTrace = (ArrayNode) errorResponse.get("stackTrace");
            if (stackTrace != null) {
                // Format stackTrace vertically
                StringBuilder formattedStackTrace = new StringBuilder();
                for (int i = 0; i < stackTrace.size(); i++) {
                    formattedStackTrace.append("\n    ").append(stackTrace.get(i).asText());
                }
                errorResponse.put("stackTrace", formattedStackTrace.toString());
            }
        }

        // Convert back to string with pretty print
        String prettyPrintedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);

        return prettyPrintedJson;
    }
}
