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

    // 锁定操作
    private void lock(String lockKey) {
        redisTemplate.opsForValue().set(lockKey, "1", LOCK_TIME_MINUTES, TimeUnit.MINUTES);
    }

    // 获取IP(AI写的，我自己看了看大概知道是个啥了)
    private String getClientIp(HttpServletRequest request) {
        String ip = null;
        // 1. 从代理头获取IP（兼容Nginx/反向代理）
        String[] headers = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "X-Real-IP"};
        for (String header : headers) {
            ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                // 多级代理取第一个非unknown的IP
                ip = ip.split(",")[0].trim();
                // 校验IP是否合法，合法则直接返回
                if (isValidIp(ip)) {
                    return ip;
                }
            }
        }

        // 2. 从request获取原生IP
        ip = request.getRemoteAddr();
        // 3. 最终校验：确保IP合法，否则返回默认值（避免null）
        return isValidIp(ip) ? ip : "0.0.0.0";
    }

    // 核心：IP合法性校验（过滤非法IP/内网IP）
    private boolean isValidIp(String ip) {
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            return false;
        }

        // 正则校验IP格式（IPv4）
        String ipRegex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        if (!ip.matches(ipRegex)) {
            return false;
        }

        return true;
    }
}