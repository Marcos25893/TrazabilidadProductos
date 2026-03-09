package com.jaroso.trazabilidad_productos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwt;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)


                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()//Autenticado todos menos login y register
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())//Solo aceptar peticiones de la app React
                .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class) //Metemos la  comprobacion de token jwt a las peticiones http
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    //Meteremos un jwtAuthenticationFilter

}
