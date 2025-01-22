package com.demo.tx.interceptor;

import com.demo.tx.constants.AppConstants;
import com.demo.tx.entity.AuditLogs;
import com.demo.tx.repository.AuditLogsRepository;
import com.demo.tx.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@Component
public class AllRequestResponseLoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditLogsRepository auditLogsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            // Wrap the request to capture its body
            CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);

            // Log request details
            System.out.println("Incoming Request Details:");
            System.out.println("URL: " + wrappedRequest.getRequestURL());
            System.out.println("Method: " + wrappedRequest.getMethod());
            System.out.println("Headers: ");
            wrappedRequest.getHeaderNames().asIterator()
                    .forEachRemaining(headerName ->
                            System.out.println(headerName + ": " + wrappedRequest.getHeader(headerName))
                    );

            // Log the request body
            String body = wrappedRequest.getCachedBody();
            System.out.println("Request Body: " + body);

            // Re-use the wrapped request for the actual processing
            request = wrappedRequest;

        } catch (IOException e) {
            // Handle the exception gracefully
            System.err.println("Error reading request body: " + e.getMessage());
            // If error, prevent further processing
            throw new RuntimeException(e);
        }

        return true; // Continue processing the request
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws URISyntaxException, IOException {
        // Log outgoing response details
        System.out.println("Outgoing Response Details:");
        String path = new URI(request.getRequestURL().toString()).getPath();
        int httpStatusCode = response.getStatus();
        System.out.println("Status: " + httpStatusCode);

        String status = httpStatusCode >= 200 && httpStatusCode < 300 ? AppConstants.SUCCESS : AppConstants.FAILED;
        System.out.println("Request to " + path + (status.equals(AppConstants.SUCCESS) ? " PASSED" : " FAILED") + " with status " + httpStatusCode);

        // Create and save audit log
        AuditLogs auditLogs = new AuditLogs(request.getHeader("rc"),
                path,
                httpStatusCode,
                status,
                DateFormatter.formatDateAsString(new Date(), AppConstants.DATE_TIME_FORMAT_01),
                new Date());
        auditLogsRepository.save(auditLogs);

        // Wrap the response to capture its body
        CachedBodyHttpServletResponse wrappedResponse = (CachedBodyHttpServletResponse) response;

        // Log the response body
        System.out.println("Response Body: " + wrappedResponse.getCachedBody());
    }
}
