package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    int healthPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze); //will use the activity_maze xml background
        textView = (TextView) findViewById(R.id.difficultyreciever);
        textView2 = (TextView) findViewById(R.id.healthpts);
        Intent receiverIntent = getIntent();
        Double receivedDifficulty = receiverIntent.getDoubleExtra("difficulty", 0.75);
        if (receivedDifficulty == 1.0) {
            textView.setText("Difficulty: Hard");
            healthPoints = 150;
            textView2.setText("HP: 150");
        } else if (receivedDifficulty == 0.75) {
            textView.setText("Difficulty: Medium");
            healthPoints = 125;
            textView2.setText("HP: 125");
        } else {
            textView.setText("Difficulty: Easy");
            healthPoints = 100;
            textView2.setText("HP: 100");
        }
        Button exitButton2 = findViewById(R.id.exitButton2);
        exitButton2.setOnClickListener(v -> {
            // Button to ending screen
            setContentView(R.layout.activity_game_end);
        });
    }
}
