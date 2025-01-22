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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@Component
public class AllRequestResponseLoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditLogsRepository auditLogsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Incoming Request:");
        System.out.println("URL: " + request.getRequestURL());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Headers: " + request.getHeaderNames());

        // Fetch and print all headers
        System.out.println("Headers:");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + ": " + request.getHeader(headerName))
                );
        return true; // Continue processing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws URISyntaxException {
        System.out.println("Outgoing Response:");
        String path = new URI(request.getRequestURL().toString()).getPath();
        int httpStatusCode = response.getStatus();
        System.out.println("Status: " + httpStatusCode);
        String status = null;
        if (httpStatusCode >= 200 && httpStatusCode < 300) {
            status = AppConstants.SUCCESS;
            System.out.println("Request to " + path + " PASSED with status " + httpStatusCode);
        } else {
            status = AppConstants.FAILED;
            System.out.println("Request to " + path + " FAILED with status " + httpStatusCode);
        }
        AuditLogs auditLogs = new AuditLogs(request.getHeader("rc"),
                path,
                httpStatusCode,
                status,
                DateFormatter.formatDateAsString(new Date(), AppConstants.DATE_TIME_FORMAT_01),
                new Date());
        auditLogsRepository.save(auditLogs);
    }
}
