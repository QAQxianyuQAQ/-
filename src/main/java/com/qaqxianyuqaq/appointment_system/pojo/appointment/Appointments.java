package com.qaqxianyuqaq.appointment_system.pojo.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointments {
    private ArrayList<Appointment> appointments;
    private Integer total;
}
