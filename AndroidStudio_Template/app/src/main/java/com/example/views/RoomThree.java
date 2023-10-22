package com.example.views;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_2340.R;
import com.example.model.PlayerMovement;
import com.example.viewmodels.RoomThreeViewModel;
import com.example.model.Player;

import java.util.ArrayList;
import java.util.List;

public class RoomThree extends AppCompatActivity {
    private Player player;
    private RoomThreeViewModel viewModel;
    private TextView scoreTextView;
    private Handler handler = new Handler();
    private ImageView avatarImageView;
    private List<ImageView> blackTilesList;

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
        viewModel = new RoomThreeViewModel(player, score, this);

        //KEYMOVEMENT
        blackTilesList = new ArrayList<>();
        room3Layout.setFocusableInTouchMode(true);

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
                    || (row == 3 && (col == 5 || col > 6))
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
                    blackTilesList.add(tilesImageView);
                } else {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams =
                        new RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room3Layout.addView(tilesImageView, redTilesParams);
            }
        }
        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams) avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 85;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);
        player.setGoalX(715);
        player.setGoalY(5);
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

        //Temporarily removing the temporary next button to navigate to the ending screen for Sprint 3.
        // Button ending = findViewById();
        // Start updating the score
        /** ending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameEndActivity(player);
            }
        }); */
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView);
        return true;
    }

    // Check if the player has reached the final exit
    private boolean hasPlayerReachedFinalExit(int row, int column) {
        int finalExitX = 14;
        int finalExitY = 2;
        // Implement your logic to check if the player is at the final exit
        // For example, you can compare the player's position (x, y) to the exit's position
        // If they match, return true; otherwise, return false
        return (row == finalExitX && column == finalExitY); // Adjust these coordinates accordingly
    }

}

