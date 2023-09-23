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
        });

        // Set difficulty based on difficulty checked
        startBtn.setOnClickListener(v -> {
            //initial config screen when enter the dungeon btn clicked
            Intent initial_config = new Intent(MainActivity.this, InitialConfigActivity.class);
            startActivity(initial_config);
            finish();
        });
    }
}