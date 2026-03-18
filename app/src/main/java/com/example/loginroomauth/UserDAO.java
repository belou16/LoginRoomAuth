package com.example.loginroomauth;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void inserer(User user);

    @Query("SELECT * FROM user WHERE name = :nom AND password = :password")
    User verifierLogin(String nom, String password);
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
}
