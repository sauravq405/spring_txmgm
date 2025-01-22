package com.demo.tx.interceptor;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream cachedBody = new ByteArrayOutputStream();
    private PrintWriter cachedWriter = new PrintWriter(cachedBody);

    public CachedBodyHttpServletResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(jakarta.servlet.WriteListener writeListener) {
                // No-op
            }

            @Override
            public void write(int b) throws IOException {
                cachedBody.write(b);
            }

            @Override
            public void flush() throws IOException {
                super.flush();
                cachedBody.flush();
            }

            @Override
            public void close() throws IOException {
                super.close();
                cachedBody.close();
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return cachedWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();
        cachedWriter.flush();
        cachedBody.flush();
    }

    public String getCachedBody() {
        return cachedBody.toString(StandardCharsets.UTF_8);
    }
}