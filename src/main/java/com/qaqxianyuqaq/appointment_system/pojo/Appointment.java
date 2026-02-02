package com.qaqxianyuqaq.appointment_system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private String name;
    private String department;
    private String startTime;
    private String endTime;
    private Integer status;
    private String statusText;
    private String createTime;
    private String updateTime;
}
