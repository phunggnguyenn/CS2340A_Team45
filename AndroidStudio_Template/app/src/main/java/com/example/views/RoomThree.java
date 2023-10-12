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
import com.example.viewmodels.RoomThreeViewModel;
import com.example.model.Player;
import com.example.viewmodels.RoomTwoViewModel;

public class RoomThree extends AppCompatActivity {
    private Player player;
    private RoomThreeViewModel viewModel;
    private TextView scoreTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        Intent receiverIntent = getIntent();
        int score = receiverIntent.getIntExtra("score", 1000);
        player = (Player) receiverIntent.getSerializableExtra("player");
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        RelativeLayout room3Layout = findViewById(R.id.room3Layout);
        viewModel = new RoomThreeViewModel(player, score);
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
        ImageView avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.updateScore(-1);
                scoreTextView.setText("Score: " + viewModel.getScore());
                handler.postDelayed(this, 1000);
            }
        }, 1000);

        Button ending = findViewById(R.id.endingscreen);
        // Start updating the score
        ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameEndActivity(player);
            }
        });
    }

    private void startGameEndActivity(Player player) {
        Intent endIntent = new Intent(this, GameEndActivity.class);
        endIntent.putExtra("player", player);
        endIntent.putExtra("score", viewModel.getScore());
        startActivity(endIntent);
        finish(); // Finish the room3 activity
    }
    public void updateScore(int change) {
        viewModel.getScore();
    }
    public int getScore() {
        return viewModel.getScore();
    }
}

