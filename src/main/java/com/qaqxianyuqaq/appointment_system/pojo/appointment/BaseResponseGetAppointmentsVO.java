package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponseGetAppointmentsVO {
    private boolean success;
    private Integer code;
    private String message;
    private Appointments data;
}
