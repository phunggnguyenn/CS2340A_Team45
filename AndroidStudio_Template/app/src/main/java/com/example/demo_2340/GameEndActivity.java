package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEndActivity extends AppCompatActivity {
    private int score;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 0);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
    }
}
