package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private String name;
    private String department;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private String statusText;
    private LocalDateTime createTime;
    private LocalDateTime changeTime;

    public Appointment(String name, String department, LocalDateTime startTime, LocalDateTime endTime, int status, String statusText, LocalDateTime createTime, LocalDateTime changeTime) {
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
