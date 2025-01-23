package com.demo.tx.interceptor;

import com.demo.tx.constants.AppConstants;
import com.demo.tx.entity.AuditLogs;
import com.demo.tx.repository.AuditLogsRepository;
import com.demo.tx.utils.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@Component
public class AllRequestResponseLoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditLogsRepository auditLogsRepository;

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            // Wrap the request to capture its body
            CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest(request);

            // Log request details
            log.debug("Incoming Request Details:");
            log.debug("URL: {}", wrappedRequest.getRequestURL());
            log.debug("Method: " + wrappedRequest.getMethod());
            log.debug("Headers: ");
            wrappedRequest.getHeaderNames().asIterator()
                    .forEachRemaining(headerName ->
                            log.debug(headerName + ": " + wrappedRequest.getHeader(headerName))
                    );

            // Log the request body
            String body = wrappedRequest.getCachedBody();
            log.debug("Request Body: {}", body);

            // Re-use the wrapped request for the actual processing
            request = wrappedRequest;

        } catch (IOException e) {
            // Handle the exception gracefully
            log.error("Error reading request body: {}", e.getMessage());
            // If error, prevent further processing
            throw new RuntimeException(e);
        }

        return true; // Continue processing the request
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws URISyntaxException, IOException {
        // Log outgoing response details
        log.debug("Outgoing Response Details:");
        String path = new URI(request.getRequestURL().toString()).getPath();
        int httpStatusCode = response.getStatus();
        log.debug("Status: {}", httpStatusCode);

        String status = httpStatusCode >= 200 && httpStatusCode < 300 ? AppConstants.SUCCESS : AppConstants.FAILED;
        log.debug("Request to " + path + (status.equals(AppConstants.SUCCESS) ? " PASSED" : " FAILED") + " with status " + httpStatusCode);

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
        log.debug("Response Body: {}", wrappedResponse.getCachedBody());
    }
}
