package com.example.loginroomauth;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void inserer(User user);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User verifierLogin(String email, String password);

    @Query("SELECT * FROM user")
    List<User> listUser();
}
