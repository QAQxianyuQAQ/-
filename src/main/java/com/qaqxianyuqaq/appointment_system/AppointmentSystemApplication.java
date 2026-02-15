package com.qaqxianyuqaq.appointment_system;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppointmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentSystemApplication.class, args);
    }

}
