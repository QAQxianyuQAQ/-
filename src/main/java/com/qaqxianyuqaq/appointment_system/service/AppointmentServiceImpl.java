package com.qaqxianyuqaq.appointment_system.service;

import com.qaqxianyuqaq.appointment_system.dao.AppointmentMapper;
import com.qaqxianyuqaq.appointment_system.pojo.appointment.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Resource
    private AppointmentMapper appointmentMapper;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean FormatCheck(String startTimeStr,String endTimeStr) {
        if (startTimeStr == null || startTimeStr.trim().isEmpty() ||
                endTimeStr == null || endTimeStr.trim().isEmpty()) {
            return true;
        }

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        try {
            startTime = LocalDateTime.parse(startTimeStr.trim(), DATE_TIME_FORMATTER);
            endTime = LocalDateTime.parse(endTimeStr.trim(), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return true;
        }

        return !startTime.isBefore(endTime);
    }

    @Override
    public boolean CompleteCheck(String startTimeStr) {
        return LocalDateTime.now().isAfter(LocalDateTime.parse(startTimeStr.trim(), DATE_TIME_FORMATTER));
    }

    @Override
    public BaseResponseGetAppointmentsVO getAppointments() {
        ArrayList<Appointment> appointments = appointmentMapper.findAll();

        if(appointments.isEmpty()) {
            return new BaseResponseGetAppointmentsVO(true,200, "暂无数据", null);
        }
        for (Appointment appointment : appointments) {
            if (this.CompleteCheck(appointment.getStartTime())) {
                appointment.setStatus(1);
                appointment.setStatusText("已完成");
                appointmentMapper.update(appointment);
            }
        }
        Appointments data = new Appointments(appointments, appointments.size());
        return new BaseResponseGetAppointmentsVO(true,200, "查询成功", data);
    }
    @Override
    public BaseResponseAppointment addAppointment(AddAppointmentDataDTO addAppointmentDataDTO) {
        if(FormatCheck(addAppointmentDataDTO.getStartTime(), addAppointmentDataDTO.getEndTime())) {
            return new BaseResponseAppointment(400, "时间格式错误", null);
        }
        Appointment appointment = new Appointment(
                addAppointmentDataDTO.getCustomer_name(),
                addAppointmentDataDTO.getDepartment(),
                addAppointmentDataDTO.getStartTime(),
                addAppointmentDataDTO.getEndTime(),
                0,
                "待开始",
                java.time.LocalDateTime.now().toString(),
                java.time.LocalDateTime.now().toString()
        );

        try {
            appointmentMapper.insert(appointment);
        } catch (Exception e) {
            return new BaseResponseAppointment(500,"添加失败",null);
        }

        return new BaseResponseAppointment(200, "添加成功", appointment);
    }

    @Override
    public BaseResponseAppointment updateAppointment(UpdateAppointmentsDTO updateAppointmentsDTO) {
        if(FormatCheck(updateAppointmentsDTO.getStartTime(), updateAppointmentsDTO.getEndTime())) {
            return new BaseResponseAppointment(400, "时间格式错误", null);
        }

        Appointment appointment = new Appointment();
        appointment.setId(updateAppointmentsDTO.getId());
        appointment.setChangeTime(java.time.LocalDateTime.now().toString());
        appointment.setStartTime(updateAppointmentsDTO.getStartTime());
        appointment.setEndTime(updateAppointmentsDTO.getEndTime());

        if(!this.CompleteCheck(updateAppointmentsDTO.getStartTime())){
            appointment.setStatus(0);
            appointment.setStatusText("待开始");
        }

        try {
            appointmentMapper.update(appointment);
        } catch (Exception e) {
            return new BaseResponseAppointment(500,"更新失败",null);
        }
        return new BaseResponseAppointment(200, "更新成功", appointment);
    }

    @Override
    public BaseResponseBoolean deleteAppointment(Integer id) {
        try {
            appointmentMapper.delete(id);
        } catch (Exception e) {
            return new BaseResponseBoolean(500,false,"删除失败");
        }
        return new BaseResponseBoolean(200,true, "删除成功");
    }
}
