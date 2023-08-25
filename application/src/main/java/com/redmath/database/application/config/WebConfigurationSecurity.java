package com.redmath.database.application.config;

import org.apache.commons.collections4.Get;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.http.HttpRequest;
import java.security.Security;

@Configuration
public class WebConfigurationSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(config -> config.requestMatchers(new AntPathRequestMatcher("/api/v1/News/1", "GET")).permitAll().anyRequest().authenticated());
        return http.build();
    }
}
