package com.arka.MSConfigServer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF globalmente (menos seguro, pero simplifica la prueba)
                .authorizeHttpRequests(auth -> auth
                        // Esto es lo clave: ignora la seguridad para todos los endpoints de Actuator
                        .requestMatchers("/actuator/**").authenticated() // O .permitAll() si quieres probar sin autenticación
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}


/*
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/actuator/**"))
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.POST, "/actuator/bus-refresh").authenticated()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
*/