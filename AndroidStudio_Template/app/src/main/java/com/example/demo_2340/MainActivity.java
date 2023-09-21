package com.example.demo_2340;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


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

        // Set difficulty based on difficulty checked
        startBtn.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            double difficulty = 1;

            switch (difficultyRadioGroup.getCheckedRadioButtonId()) {
                case R.id.radioEasy:
                    difficulty = 0.5;
                    break;
                case R.id.radioMedium:
                    difficulty = 0.75;
                    break;
                case R.id.radioHard:
                    difficulty = 1;
                    break;
                default:
                    difficulty = 0.5;
                    break;
            }
            //calling home screen- game activity
            Intent game = new Intent(MainActivity.this, GameActivity.class);
            //initial config screen pending

            //calling maze game screen when enter the dungeon btn clicked
            Intent game2 = new Intent(MainActivity.this, MazeGameActivity.class);
            game.putExtra("difficulty", difficulty);
            startActivity(game);
            startActivity(game2);
            finish();
        });
    }
}