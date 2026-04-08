package com.medilabo.microserviceassessment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.urls")
@Component
@Data
public class UrlConfig {

    private String patients;
    private String notes;
}
