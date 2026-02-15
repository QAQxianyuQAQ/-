package com.qaqxianyuqaq.appointment_system.config;

import com.qaqxianyuqaq.appointment_system.filter.JwtFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private JwtFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/register").permitAll()

                        // /appointments 权限控制
                        .requestMatchers(HttpMethod.GET, "/appointments").hasAuthority("users")
                        .requestMatchers("/appointments").hasAuthority("admin")

                        // /appointments/{id} 权限控制
                        .requestMatchers(HttpMethod.DELETE, "/appointments/{id}").hasAuthority("admin")
                        .requestMatchers("/appointments/{id}").hasAnyAuthority("users", "admin")

                        // /appointments/update 权限控制
                        .requestMatchers(HttpMethod.POST, "/appointments/update").hasAuthority("admin")
                        .requestMatchers("/appointments/update").hasAnyAuthority("users", "admin")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}