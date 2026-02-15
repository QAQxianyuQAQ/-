package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.appointment.*;

import java.time.LocalDateTime;

public interface AppointmentService {
    public boolean FormatCheck(LocalDateTime startTime,LocalDateTime endTime);
    public boolean CompleteCheck(LocalDateTime startTime);

    public BaseResponseGetAppointmentsVO getAppointments();

    public BaseResponseAppointment addAppointment(AddAppointmentDataDTO addAppointmentDataDTO);

    public BaseResponseAppointment updateAppointment(UpdateAppointmentsDTO updateAppointmentsDTO);

    public BaseResponseBoolean deleteAppointment(Integer id);
}
