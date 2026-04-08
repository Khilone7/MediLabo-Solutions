package com.medilabo.microservicefront.configuration;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public RestTemplate restTemplate(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String password = (String) session.getAttribute("userPassword");
        RestTemplate restTemplate = new RestTemplate();
        if (auth != null) {
            String username = auth.getName();
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
        } else {
            throw new AuthenticationException("Authentication information is not available in the security context.") {
            };
        }
        return restTemplate;
    }
}
