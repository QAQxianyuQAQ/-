package com.qaqxianyuqaq.appointment_system.pojo.appointment;

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
    private String changeTime;

    public Appointment(String name, String department, String startTime, String endTime, int status, String statusText, String createTime, String changeTime) {
        this.name = name;
        this.department = department;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.statusText = statusText;
        this.createTime = createTime;
        this.changeTime = changeTime;
    }
}
