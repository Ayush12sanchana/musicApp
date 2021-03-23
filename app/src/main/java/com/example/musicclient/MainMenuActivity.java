package com.example.musicclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainMenuActivity extends AppCompatActivity {

    RelativeLayout offlineButton, onlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        offlineButton = findViewById(R.id.offline);
        onlineButton = findViewById(R.id.online);


        //Online Button Function start
        onlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainMenuActivity.this, OnlineMusicMenu.class);
                startActivity(intent);
            }
        });

        //Offline Button Function start
        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainMenuActivity.this, SongsActivity.class);
                startActivity(intent);
            }
        });

    }
}
