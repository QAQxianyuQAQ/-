package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.Appointment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AppointmentMapper {
    @Select("select * from appointments")
    public ArrayList<Appointment> findAll();

    @Insert("insert into appointments(name,department,startTime,endTime,status,statusText,createTime,changeTime) values(#{name},#{department},#{startTime},#{endTime},#{status},#{statusText},#{createTime},#{changeTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Appointment appointment);

    @Update("update appointments set name = #{name},department = #{department},startTime = #{startTime},endTime = #{endTime},status = #{status},statusText = #{statusText},createTime = #{createTime},changeTime = #{changeTime} where id = #{id}")
    public int update(Appointment appointment);
}
