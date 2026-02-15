package com.qaqxianyuqaq.appointment_system.config;

final public class LimiterConfig {
    public static final int MAX_ATTEMPTS = 5;
    public static final long LOCK_TIME_MINUTES = 10;
    public static final long WINDOW_TIME_SECONDS = 60;
    private LimiterConfig() {}
}
