package com.qaqxianyuqaq.appointment_system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    // 密钥（其实藏了个flag）
    private static final String SECRET = "fSEhIXFLdnBfM2JlZF9rX254ZTBQX2UwSSshISFjeDAxZGsxZW5rYnF4eU17aWhq";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    // 生成JWT
    public static String generateToken(String username,String userType) {
        return Jwts.builder()
                .subject(username)
                .claim("userType",userType)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
    // 解析JWT
    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    // 获取用户名
    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
    // 获取用户类型
    public static String getUserType(String token) {
        Claims claims = parseToken(token);
        return claims.get("userType").toString();
    }
}
