package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentsDTO {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
}
