package com.qaqxianyuqaq.appointment_system.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String userType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean idDelete;
}
