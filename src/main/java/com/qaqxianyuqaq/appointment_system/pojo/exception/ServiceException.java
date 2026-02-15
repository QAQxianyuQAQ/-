package com.qaqxianyuqaq.appointment_system.pojo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException{
    private String message;
    private Integer code;
    private String data;

    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.data = null;
    }
}
