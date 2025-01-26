package com.demo.tx.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class StackTraceSerializer extends StdSerializer<ArrayNode> {

    public StackTraceSerializer() {
        this(null);
    }

    public StackTraceSerializer(Class<ArrayNode> t) {
        super(t);
    }

    @Override
    public void serialize(ArrayNode stackTrace, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (int i = 0; i < stackTrace.size(); i++) {
            // Write each stack trace element on a new line with proper indentation
            gen.writeRaw("\n    ");
            gen.writeString(stackTrace.get(i).asText());
        }
        gen.writeEndArray();
    }
}