package com.qaqxianyuqaq.appointment_system.dao;

import com.qaqxianyuqaq.appointment_system.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    // 查询
    @Select("select * from users where username = #{username}")
    public ArrayList<User> findByUsername(@Param("username")String username);

    @Select("select * from users where email = #{email} and password = #{password}")
    public ArrayList<User> findByEmail(@Param("email")String email);

    @Select("select * from users")
    public ArrayList<User> findAll();
    // 计数
    @Select("Select count(*) from users")
    public int count();
    // 修改
    @Update("update users set username = #{username}, email = #{email}, password = #{password}, userType = #{userType}, createTime = #{createTime}, updateTime = #{updateTime}, idDelete = #{idDelete} where id = #{id}")
    public int update(User user);
    // 添加
    @Update("insert into users(username, email, password, userType, createTime, updateTime, idDelete) values(#{username}, #{email}, #{password}, #{userType}, #{createTime}, #{updateTime}, #{idDelete})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(User user);
}
