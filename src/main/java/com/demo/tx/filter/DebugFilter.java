package com.demo.tx.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import com.demo.tx.interceptor.CachedBodyHttpServletRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("debug")
public class DebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest instanceof CachedBodyHttpServletRequest) {
            System.out.println("Debug - Before filter chaining: " + ((CachedBodyHttpServletRequest) httpRequest).getCachedBody());
        } else {
            System.out.println("Debug - Before filter chaining: Request not wrapped.");
        }
        chain.doFilter(request, response);
        if (httpRequest instanceof CachedBodyHttpServletRequest) {
            System.out.println("Debug - After filter chaining: " + ((CachedBodyHttpServletRequest) httpRequest).getCachedBody());
        } else {
            System.out.println("Debug - After filter chaining: Request not wrapped.");
        }
    }
}