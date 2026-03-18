package com.example.loginroomauth;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public User(String name, String email, String password) {
    }

    public String getName() {
        String name;
        return name;
    }

    public String getEmail() {
        String email;
        return email;
    }
}
