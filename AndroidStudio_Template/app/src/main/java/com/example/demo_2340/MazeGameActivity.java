package com.example.demo_2340;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze); //will use the activity_maze xml background
    }
}
