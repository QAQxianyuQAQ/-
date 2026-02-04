package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.pojo.appointment.*;

public interface AppointmentService {
    public boolean FormatCheck(String startTimeStr,String endTimeStr);
    public boolean CompleteCheck(String startTimeStr);
    public BaseResponseGetAppointmentsVO getAppointments();

    public BaseResponseAppointment addAppointment(AddAppointmentDataDTO addAppointmentDataDTO);

    public BaseResponseAppointment updateAppointment(UpdateAppointmentsDTO updateAppointmentsDTO);

    public BaseResponseBoolean deleteAppointment(Integer id);
}
