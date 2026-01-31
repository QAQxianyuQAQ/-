package com.qaqxianyuqaq.appointmentsystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 关闭所有请求的认证要求
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 所有请求都放行，无需认证
                )
                // 2. 禁用默认的表单登录（核心：关闭默认login页面）
                .formLogin(AbstractHttpConfigurer::disable)
                // 3. 禁用HTTP Basic认证（可选，避免弹窗）
                .httpBasic(AbstractHttpConfigurer::disable)
                // 4. 禁用CSRF（开发环境可选，生产环境谨慎）
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}