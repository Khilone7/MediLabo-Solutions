package com.medilabo.microservicefront.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.urls")
public class UrlConfig {

    private String patients;
    private String notes;
    private String assessment;
}
