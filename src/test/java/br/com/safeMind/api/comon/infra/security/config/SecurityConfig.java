package br.com.safeMind.api.comon.infra.security.config;

import br.com.safeMind.api.comon.infra.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration

public class SecurityConfig {

    @Autowired
    CustomUserDetailService service;

    @Autowired
    SecurityFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity ) throws Exception {

    }
}
