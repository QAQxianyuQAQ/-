package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    @Select("select * from user where email = #{email} and password = #{password}")
    public User findByEmailAndPassword(@Param("email")String email,@Param("password")String password);

    @Select("select * from user")
    public ArrayList<User> findAll();

    @Select("Select count(*) from user")
    public int count();

    @Update("update user set username = #{username}, email = #{email}, password = #{password}, userType = #{userType}, createTime = #{createTime}, updateTime = #{updateTime}, idDelete = #{idDelete}")
    public int update(User user);
}
