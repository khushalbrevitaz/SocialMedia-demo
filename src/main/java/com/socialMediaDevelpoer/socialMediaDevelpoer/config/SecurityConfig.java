package com.socialMediaDevelpoer.socialMediaDevelpoer.config;

import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtAuthEntryPoint;
import com.socialMediaDevelpoer.socialMediaDevelpoer.security.JwtAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    // Create JwtAuthFilter bean
    @Bean
    public Filter jwtAuthFilter(){
        return new JwtAuthFilter();
    }

    // Create PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Create SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CORS
        http.cors().disable();

        // Disable CSRF
        http.csrf().disable();

        // Change session management to STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add exception handler
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint);

        // Authorize http requests
        http.authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/auth/register/**").permitAll()
                .requestMatchers("/v3/api-docs").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/user/login/**").permitAll()
                .requestMatchers("/admin/login/**").permitAll()
                .anyRequest().authenticated();

        // Add JWT authentication filter
        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

