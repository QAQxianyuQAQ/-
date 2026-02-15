package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAppointmentDataDTO {
    private String customer_name;
    private String department;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
}
