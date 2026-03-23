package com.goros.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/default"))
                .authorizeHttpRequests( auth -> auth.requestMatchers("/default").permitAll().requestMatchers("/user").hasAnyRole("USER", "ADMIN").requestMatchers("/admin").hasRole("ADMIN")).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}