package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentsDTO {
    private Integer id;
    private String startTime;
    private String endTime;
    private String reason;
}
