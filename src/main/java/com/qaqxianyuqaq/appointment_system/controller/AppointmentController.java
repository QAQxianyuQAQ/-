package com.qaqxianyuqaq.appointment_system.controller;

import com.qaqxianyuqaq.appointment_system.pojo.appointment.*;
import com.qaqxianyuqaq.appointment_system.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping
    public BaseResponseGetAppointmentsVO getAppointments() {
        return appointmentService.getAppointments();
    }

    @PostMapping
    public BaseResponseAppointment addAppointment(@RequestBody AddAppointmentDataDTO AddAppointmentDataDTO) {
        return appointmentService.addAppointment(AddAppointmentDataDTO);
    }

    @PostMapping("/update")
    public BaseResponseAppointment updateAppointment(@RequestBody UpdateAppointmentsDTO updateAppointmentsDTO) {
        return appointmentService.updateAppointment(updateAppointmentsDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponseBoolean deleteAppointment(@PathVariable Integer id) {
        return appointmentService.deleteAppointment(id);
    }
}
