package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    public User findByUsername(String username);

    @Select("select * from user where email = #{email}")
    public User findByEmail(String email);

    @Select("select * from user")
    public ArrayList<User> findAll();

    @Update("update user set username = #{username}, email = #{email}, password = #{password}, userType = #{userType}, createTime = #{createTime}, updateTime = #{updateTime}, idDelete = #{idDelete} where id = #{id}")
    public int update(User user);
}
