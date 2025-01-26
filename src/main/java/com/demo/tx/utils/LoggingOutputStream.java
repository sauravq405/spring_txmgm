package com.demo.tx.utils;

import org.slf4j.Logger;
import java.io.IOException;
import java.io.OutputStream;

public class LoggingOutputStream extends OutputStream {
    private final Logger logger;
    private final StringBuilder buffer = new StringBuilder();

    public LoggingOutputStream(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void write(int b) throws IOException {
        buffer.append((char) b);
        if (b == '\n' || buffer.length() > 1024) {
            logger.debug(buffer.toString());
            buffer.setLength(0);
        }
    }

    @Override
    public void flush() throws IOException {
        if (buffer.length() > 0) {
            logger.debug(buffer.toString());
            buffer.setLength(0);
        }
    }

    @Override
    public void close() throws IOException {
        flush();
    }
}