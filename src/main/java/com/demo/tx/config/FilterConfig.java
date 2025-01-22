package com.demo.tx.config;

import com.demo.tx.filter.DebugFilter;
import com.demo.tx.filter.RequestResponseWrappingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RequestResponseWrappingFilter> requestResponseWrappingFilterRegistration(RequestResponseWrappingFilter filter) {
        FilterRegistrationBean<RequestResponseWrappingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1); // Make this one of the first filters
        return registration;
    }

    @Bean
    @Profile("debug") // Add this annotation to ensure the bean is only created in the 'debug' profile
    public FilterRegistrationBean<DebugFilter> debugFilterRegistration(DebugFilter debugFilter) {
        FilterRegistrationBean<DebugFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(debugFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(2); // Run after the wrapping filter
        return registration;
    }
}