package com.qaqxianyuqaq.appointment_system.interceptor;

import com.qaqxianyuqaq.appointment_system.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if ("/users/login".equals(requestURI) || "/users/register".equals(requestURI)) {
            return true;
        }

        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            return true;
        }

        try {
            Claims claims = jwtUtil.parseToken(token);
            String username = claims.getSubject();
            String userType = claims.get("userType").toString();

            String newToken = jwtUtil.generateToken(username, userType);
            response.setHeader("newtoken", newToken);
        } catch (Exception e) {
            // spring security会出手
        }

        return true;
    }
}