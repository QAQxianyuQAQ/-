package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAppointmentDataDTO {
    private String customer_name;
    private String department;
    private String startTime;
    private String endTime;
    private String reason;
}
