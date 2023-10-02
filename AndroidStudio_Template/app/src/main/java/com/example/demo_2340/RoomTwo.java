package com.example.demo_2340;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoomTwo extends AppCompatActivity {
    private int score;
    private TextView scoreTextView;
    private Handler handler = new Handler();
    private Runnable scoreUpdater = new Runnable() {
        @Override
        public void run() {
            updateScore(-1);
            handler.postDelayed(this,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        // Retrieve the score value from the Intent
        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 1000);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        RelativeLayout room2Layout = findViewById(R.id.room2Layout);
        Intent receiverIntent = getIntent();
        String playerName = receiverIntent.getStringExtra("playerName");
        Double receivedDifficulty = receiverIntent.getDoubleExtra("difficulty", 0.75);
        // tile dimensions
        int tileWidth = 80;
        int tileHeight = 80;

        // # rows and cols in room3 grid
        int numRows = 14;
        int numColumns = 12;

        int margin = 10;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {

                int left = col * (tileWidth + margin);
                int top = row * (tileHeight + margin);

                ImageView tilesImageView = new ImageView(this);
                if ((row == 0 && (col != 1)
                    || (row == 1 && ((col == 0) || (col > 2 && col < 5) || (col == 7) || (col == 9)))
                    || (row == 2 && (col == 2 || col == 4 || col == 9))
                    || (row == 3 && (col == 2 || col == 6 || (col > 7 && col < 10)))
                    || (row == 4 && (col == 3 || col == 8 || col == 10 || (col > 4 && col < 7)))
                    || (row == 5 && (col < 2))
                    || (row == 6 && ((col == 0) || (col > 2 && col < 5) || (col > 5 && col < 8)))
                    || (row == 7 && (((col > 6) && (col < 10)) || (col == 3) || col == 11))
                    || (row == 8 && ((col > 1 && col < 6) || col == 7))
                    || (row == 9 && (col == 2 || col == 4 || col > 9))
                    || (row == 11 && (col > 0))
                    || (row == 13 && col != 8))) {
                    tilesImageView.setImageResource(R.drawable.blacktile3);
                } else {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams = new RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room2Layout.addView(tilesImageView, redTilesParams);
            }
        }
        Button room3btn = findViewById(R.id.room3btn);
        // Start updating the score
        handler.postDelayed(scoreUpdater, 1000);
        room3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom3Activity(playerName, receivedDifficulty);
            }
        });
    }
    private void startRoom3Activity(String playerName, double receivedDifficulty) {
        Intent room3Intent = new Intent(this, RoomThree.class);
        room3Intent.putExtra("playerName", playerName);
        room3Intent.putExtra("difficulty", receivedDifficulty);
        room3Intent.putExtra("score", score);
        startActivity(room3Intent);
        finish(); // Finish the room2 activity
    }
    private void updateScore(int change) {
        score += change;
        if (score < 0) {
            score = 0; // Ensure the score doesn't go below 0
        }
        // Update the TextView to display the updated score
        scoreTextView.setText("Score: " + score);
    }
}

