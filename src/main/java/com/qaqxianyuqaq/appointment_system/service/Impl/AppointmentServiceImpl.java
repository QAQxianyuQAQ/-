package com.qaqxianyuqaq.appointment_system.service.Impl;

import com.qaqxianyuqaq.appointment_system.dao.AppointmentMapper;
import com.qaqxianyuqaq.appointment_system.pojo.appointment.*;
import com.qaqxianyuqaq.appointment_system.pojo.exception.ServiceException;
import com.qaqxianyuqaq.appointment_system.service.AppointmentService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Resource
    private AppointmentMapper appointmentMapper;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //校验输入时间开始先于结束
    @Override
    public boolean FormatCheck(LocalDateTime startTime,LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return true;
        }

        return !startTime.isBefore(endTime);
    }
    //校验会议是否已完成
    @Override
    public boolean CompleteCheck(LocalDateTime startTime) {
        return LocalDateTime.now().isAfter(startTime);
    }
    //获取所有预约
    @Override
    public BaseResponseGetAppointmentsVO getAppointments() {
        ArrayList<Appointment> appointments = appointmentMapper.findAll();

        if(appointments.isEmpty()) {
            return new BaseResponseGetAppointmentsVO(true,HttpStatus.OK.value(), "暂无数据", null);
        }
        for (Appointment appointment : appointments) {
            if (this.CompleteCheck(appointment.getStartTime())) {
                appointment.setStatus(1);
                appointment.setStatusText("已完成");
                appointmentMapper.update(appointment);
            }
        }
        Appointments data = new Appointments(appointments, appointments.size());
        return new BaseResponseGetAppointmentsVO(true,HttpStatus.OK.value(), "查询成功", data);
    }
    //添加预约
    @Override
    public BaseResponseAppointment addAppointment(AddAppointmentDataDTO addAppointmentDataDTO) {
        if(FormatCheck(addAppointmentDataDTO.getStartTime(), addAppointmentDataDTO.getEndTime())) {
            throw new ServiceException("时间格式错误", HttpStatus.BAD_REQUEST.value());
        }
        Appointment appointment = new Appointment(
                addAppointmentDataDTO.getCustomer_name(),
                addAppointmentDataDTO.getDepartment(),
                addAppointmentDataDTO.getStartTime(),
                addAppointmentDataDTO.getEndTime(),
                0,
                "待开始",
                java.time.LocalDateTime.now(),
                java.time.LocalDateTime.now()
        );

        appointmentMapper.insert(appointment);

        return new BaseResponseAppointment(HttpStatus.OK.value(), "添加成功", appointment);
    }
    //修改预约
    @Override
    public BaseResponseAppointment updateAppointment(UpdateAppointmentsDTO updateAppointmentsDTO) {
        if(FormatCheck(updateAppointmentsDTO.getStartTime(), updateAppointmentsDTO.getEndTime())) {
            throw new ServiceException("时间格式错误", HttpStatus.BAD_REQUEST.value());
        }

        Appointment appointment = new Appointment();
        appointment.setId(updateAppointmentsDTO.getId());
        appointment.setChangeTime(java.time.LocalDateTime.now());
        appointment.setStartTime(updateAppointmentsDTO.getStartTime());
        appointment.setEndTime(updateAppointmentsDTO.getEndTime());

        if(!this.CompleteCheck(updateAppointmentsDTO.getStartTime())){
            appointment.setStatus(0);
            appointment.setStatusText("待开始");
        }

        appointmentMapper.update(appointment);
        return new BaseResponseAppointment(HttpStatus.OK.value(), "更新成功", appointment);
    }
    //删除预约
    @Override
    public BaseResponseBoolean deleteAppointment(Integer id) {
        if(appointmentMapper.findById(id) == null) {
            throw new ServiceException("预约不存在", HttpStatus.BAD_REQUEST.value());
        }
        appointmentMapper.delete(id);
        return new BaseResponseBoolean(HttpStatus.OK.value(),true, "删除成功");
    }
}
