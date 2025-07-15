package com.eazybytes.urlshortnermicroservice.config;

import com.eazybytes.loggingmiddleware.service.LoggingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }
}
