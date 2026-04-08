package com.medilabo.microserviceassessment.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public RestTemplate restTemplate(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((req, body, execution) -> {
            req.getHeaders().set("Authorization", authHeader);
            return execution.execute(req, body);
        });
        return restTemplate;
    }
}
