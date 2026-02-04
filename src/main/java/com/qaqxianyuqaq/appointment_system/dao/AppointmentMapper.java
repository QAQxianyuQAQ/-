package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.appointment.Appointment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AppointmentMapper {
    //查询
    @Select("select * from appointments")
    public ArrayList<Appointment> findAll();
    //添加
    @Insert("insert into appointments(name,department,startTime,endTime,status,statusText,createTime,changeTime) values(#{name},#{department},#{startTime},#{endTime},#{status},#{statusText},#{createTime},#{changeTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Appointment appointment);
    //修改
    @Update("update appointments set startTime = #{startTime},endTime = #{endTime},changeTime = #{changeTime} where id = #{id}")
    public int update(Appointment appointment);
    //删除
    @Delete("delete from appointments where id = #{id}")
    public int delete(int id);
}
