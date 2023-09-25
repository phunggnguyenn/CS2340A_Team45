package com.example.demo_2340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = findViewById(R.id.startButton);
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            // exit the game
            finish();
        });

        // When start button is clicked
        startBtn.setOnClickListener(v -> {
            Intent initialConfig = new Intent(MainActivity.this, InitialConfigActivity.class);
            startActivity(initialConfig);
        });
    }
}