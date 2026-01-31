package com.qaqxianyuqaq.appointmentsystem.Dao;

import com.qaqxianyuqaq.appointmentsystem.Data.User;

public interface UserDao {
    public User findByUsername(String username);
}
