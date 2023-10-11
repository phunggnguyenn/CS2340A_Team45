package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_2340.R;
import com.example.views.GameEndActivity;

public class RoomThree extends AppCompatActivity {
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
        setContentView(R.layout.activity_room3);

        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 1000);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        RelativeLayout room3Layout = findViewById(R.id.room3Layout);
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
                if ((row == 0 && (col != 10)
                    || (row == 1 && ((col == 0) || (col == 5) || (col == 9)))
                    || (row == 2 && (col == 3 || col == 5 || col < 2))
                    || (row == 3 && (col == 5 || col > 6 ))
                    || (row == 4 && (col == 5 || col == 7 || col == 9 || col == 11))
                    || (row == 5 && ((col > 0 && col < 4) || col == 5))
                    || (row == 6 && (col == 3 || col == 6 || col == 8))
                    || (row == 7 && (((col > 0) && (col < 4)) || (col == 8)))
                    || (row == 8 && ((col > 7 && col < 10) || col == 3))
                    || (row == 9 && (col == 7))
                    || (row == 10 && ((col > 1 && col < 6) || (col > 8)))
                    || (row == 11 && (col == 2 || col == 4 || col == 9 || col == 11))
                    || (row == 13 && col != 1))) {
                    tilesImageView.setImageResource(R.drawable.blacktile3);
                } else {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams = new RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room3Layout.addView(tilesImageView, redTilesParams);
            }
        }
        Button ending = findViewById(R.id.endingscreen);
        handler.postDelayed(scoreUpdater, 1000);
        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameEndActivity(playerName, receivedDifficulty);
            }
        });
    }
    private void startGameEndActivity(String playerName, double receivedDifficulty) {
        Intent endIntent = new Intent(this, GameEndActivity.class);
        endIntent.putExtra("playerName", playerName);
        endIntent.putExtra("difficulty", receivedDifficulty);
        endIntent.putExtra("score", score);
        startActivity(endIntent);
        finish(); // Finish the room3 activity
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

