package com.qaqxianyuqaq.appointment_system.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseLoginDTO {
    private Integer code;
    private LoginVO data;
    private String message;
}
