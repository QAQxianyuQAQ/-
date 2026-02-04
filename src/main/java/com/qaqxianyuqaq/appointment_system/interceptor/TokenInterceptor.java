package com.qaqxianyuqaq.appointment_system.interceptor;

import com.qaqxianyuqaq.appointment_system.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String token = request.getHeader("token");
        // token为空
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String userType;
        // 解析token
        try {
            userType = JwtUtil.getUserType(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String method = request.getMethod();
        // 用户权限
        if ("/appointments".equals(requestURI)) {
            if ("users".equals(userType)) {
                if (!"GET".equals(method)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }else if ("admin".equals(userType)) {
                return true;
            }
        }

        if (requestURI.matches("/appointments/\\d+")) {
            if ("users".equals(userType)) {
                if ("DELETE".equals(method)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            } else if ("admin".equals(userType)) {
                return true;
            }
        }

        if ("/appointments/update".equals(requestURI)) {
            if ("users".equals(userType)) {
                if ("POST".equals(method)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            } else if ("admin".equals(userType)) {
                return true;
            }
        }

        return true;
    }
}
