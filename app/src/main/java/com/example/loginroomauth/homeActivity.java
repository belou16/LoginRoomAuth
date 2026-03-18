package com.example.loginroomauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity {

    // private AppDatabase db;
    private SharedPreferences prefs;
    ListView listView;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alluser);

        listView = findViewById(R.id.listViewUsers);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "db")
                .allowMainThreadQueries()
                .build();
        Object userDao = db.userDao();

        List<User> userList = userDao.getClass();

        List<String> displayList = new ArrayList<>();

        for (User user : userList) {
            displayList.add(user.getName() + " - " + user.getEmail());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                displayList
        );

        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE); // "LoginRoomAuth", Context.MODE_PRIVATE
        String user = prefs.getString("User_name","anonyme");
        String email = prefs.getString("User_email","Email_anonyme");



        TextView nomView = findViewById(R.id.name_view);
        TextView emailView = findViewById(R.id.email_view);
        Button btnProfile = findViewById(R.id.btn_profile);
        Button btnLogout = findViewById(R.id.btn_logout);

        nomView.setText("Bienvenu " + user);
        emailView.setText(email);

        btnProfile.setOnClickListener(v -> {
            if (emailView.getVisibility() == View.VISIBLE) {
                emailView.setVisibility(View.INVISIBLE);
            } else {
                emailView.setVisibility(View.VISIBLE);
            }
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();

            editor.clear();
            editor.apply();

            Intent intent = new Intent(homeActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        });



    }
}