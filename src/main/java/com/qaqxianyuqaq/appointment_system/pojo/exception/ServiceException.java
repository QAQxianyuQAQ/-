package com.qaqxianyuqaq.appointment_system.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException {
    private String message;
    private Integer code;
    private String data;
}
