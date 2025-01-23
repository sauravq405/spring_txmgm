package com.demo.tx.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import com.demo.tx.interceptor.CachedBodyHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

@Component
@Profile("debug")
public class DebugFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest instanceof CachedBodyHttpServletRequest) {
            log.debug("Debug - Before filter chaining: {}", ((CachedBodyHttpServletRequest) httpRequest).getCachedBody());
        } else {
            log.debug("Debug - Before filter chaining: Request not wrapped.");
        }
        chain.doFilter(request, response);
        if (httpRequest instanceof CachedBodyHttpServletRequest) {
            log.debug("Debug - After filter chaining: {}", ((CachedBodyHttpServletRequest) httpRequest).getCachedBody());
        } else {
            log.debug("Debug - After filter chaining: Request not wrapped.");
        }
    }
}