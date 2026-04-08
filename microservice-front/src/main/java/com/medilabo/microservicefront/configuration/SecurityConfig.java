package com.medilabo.microservicefront.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/css/**").permitAll()
                            .anyRequest().authenticated())
                    .formLogin(form -> form
                            .successHandler((request, response, auth) -> {
                                String password = request.getParameter("password");
                                request.getSession().setAttribute("userPassword", password);
                                response.sendRedirect("http://localhost:8080/patients");
                            })
                    );
            return http.build();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails user = User.withUsername("doctor")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                    .build();
            return new InMemoryUserDetailsManager(user);
        }

}
