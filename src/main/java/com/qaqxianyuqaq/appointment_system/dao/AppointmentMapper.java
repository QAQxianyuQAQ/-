package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.appointment.Appointment;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheConfig;

import java.util.ArrayList;

@Mapper
@CacheConfig(cacheNames = "appointment")
public interface AppointmentMapper {

    @Select("select * from appointments")
    @Cacheable(key = "'all'")
    ArrayList<Appointment> findAll();

    // 根据ID查询：缓存结果
    @Select("select * from appointments where id = #{id}")
    @Cacheable(key = "#id")
    Appointment findById(int id);

    @Insert("insert into appointments(name,department,startTime,endTime,status,statusText,createTime,changeTime) values(#{name},#{department},#{startTime},#{endTime},#{status},#{statusText},#{createTime},#{changeTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @CacheEvict(key = "'all'")
    @CachePut(key = "#result.id")
    int insert(Appointment appointment);

    @Update("update appointments set startTime = #{startTime},endTime = #{endTime},changeTime = #{changeTime} where id = #{id}")
    @CacheEvict(key = "'all'")
    @CachePut(key = "#appointment.id")
    int update(Appointment appointment);

    @Delete("delete from appointments where id = #{id}")
    @CacheEvict(allEntries = true)
    int delete(int id);
}