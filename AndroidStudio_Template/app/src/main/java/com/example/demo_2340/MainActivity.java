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
            // Finish the current activity (exit the game)
            setContentView(R.layout.activity_game_end);
            finish();
        });

        // When start button is clicked
        startBtn.setOnClickListener(v -> {
            Intent initial_config = new Intent(MainActivity.this, InitialConfigActivity.class);
            startActivity(initial_config);
        });
    }
}