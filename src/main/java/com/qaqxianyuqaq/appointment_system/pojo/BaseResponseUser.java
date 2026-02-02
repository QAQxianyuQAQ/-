package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseUser {
    private Integer code;
    private User data;
    private String message;
}
