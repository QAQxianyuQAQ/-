package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseBoolean {
    private Integer code;
    private Boolean data;
    private String message;
}
