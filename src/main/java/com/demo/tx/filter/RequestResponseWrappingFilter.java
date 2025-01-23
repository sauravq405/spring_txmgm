package com.demo.tx.filter;

import com.demo.tx.interceptor.CachedBodyHttpServletRequest;
import com.demo.tx.interceptor.CachedBodyHttpServletResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestResponseWrappingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the request is for H2 Console
        if (isH2ConsoleRequest(httpRequest)) {
            // Pass the original request for H2 Console without wrapping
            chain.doFilter(request, response);
        } else {
            // Wrap request for other endpoints
            // Wrap request
            CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(httpRequest);

            // Wrap response
            CachedBodyHttpServletResponse wrappedResponse = new CachedBodyHttpServletResponse(httpResponse);

            // Pass wrapped versions to the next element in the chain
            chain.doFilter(wrappedRequest, wrappedResponse);

            // After the chain has processed, copy the response from the wrapped response back to the original response
            byte[] cachedBody = wrappedResponse.getCachedBody().getBytes();
            response.setContentLength(cachedBody.length); // Set the content length for the response
            response.getOutputStream().write(cachedBody); // Write the cached response body
            response.getOutputStream().flush(); // Ensure the buffer is flushed
        }
    }

    private boolean isH2ConsoleRequest(HttpServletRequest request) {
        // Assuming H2 Console path is /h2-console
        return request.getRequestURI().contains("/h2-console");
    }
}