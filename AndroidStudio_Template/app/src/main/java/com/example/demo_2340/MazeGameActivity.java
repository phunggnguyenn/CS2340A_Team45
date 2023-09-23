package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze); //will use the activity_maze xml background
        textView = (TextView) findViewById(R.id.difficultyreciever);
        Intent receiverIntent = getIntent();
        Double receivedValue = receiverIntent.getDoubleExtra("difficulty", 0.75);
        if (receivedValue == 1.0) {
            textView.setText("Difficulty: Hard");
        } else if (receivedValue == 0.75) {
            textView.setText("Difficulty: Medium");
        } else {
            textView.setText("Difficulty: Easy");
        }
    }
}
