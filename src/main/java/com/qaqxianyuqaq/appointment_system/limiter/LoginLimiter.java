package com.qaqxianyuqaq.appointment_system.limiter;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.qaqxianyuqaq.appointment_system.config.LimiterConfig.*;

@Service
public class LoginLimiter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    public boolean tryAcquire(String username, HttpServletRequest request) {
        String clientIp = getClientIp(request);

        String ipKey = "login:limit:ip:" + clientIp;
        String userKey = "login:limit:user:" + username;

        if (Boolean.TRUE.equals(redisTemplate.hasKey(ipKey + ":lock")) ||
                Boolean.TRUE.equals(redisTemplate.hasKey(userKey + ":lock"))) {
            return false;
        }

        Long ipCount = redisTemplate.opsForValue().increment(ipKey);
        Long userCount = redisTemplate.opsForValue().increment(userKey);

        if (ipCount == 1) {
            redisTemplate.expire(ipKey, WINDOW_TIME_SECONDS, TimeUnit.SECONDS);
        }
        if (userCount == 1) {
            redisTemplate.expire(userKey, WINDOW_TIME_SECONDS, TimeUnit.SECONDS);
        }

        if (ipCount > MAX_ATTEMPTS) {
            lock(ipKey + ":lock");
            return false;
        }
        if (userCount > MAX_ATTEMPTS) {
            lock(userKey + ":lock");
            return false;
        }

        return true;
    }
    public void resetLimit(String username, HttpServletRequest request) {
        String clientIp = getClientIp(request);
        redisTemplate.delete("login:limit:ip:" + clientIp);
        redisTemplate.delete("login:limit:user:" + username);
    }

    // 锁定操作
    private void lock(String lockKey) {
        redisTemplate.opsForValue().set(lockKey, "1", LOCK_TIME_MINUTES, TimeUnit.MINUTES);
    }

    // 获取IP
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //取第一个IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}