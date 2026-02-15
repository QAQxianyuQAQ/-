package com.qaqxianyuqaq.appointment_system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil {
    // 密钥（其实藏了个flag）
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration-time}")
    private long expirationTime;
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    // 生成JWT
    public String generateToken(String username,String userType) {
        return Jwts.builder()
                .subject(username)
                .claim("userType",userType)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }
    // 解析JWT
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    // 获取用户名
    public String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
    // 获取用户类型
    public String getUserType(String token) {
        Claims claims = parseToken(token);
        return claims.get("userType").toString();
    }
}
