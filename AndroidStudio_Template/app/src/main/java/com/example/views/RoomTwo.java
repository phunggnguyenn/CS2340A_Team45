package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo_2340.R;
import com.example.model.PlayerMovement;
import com.example.viewmodels.RoomTwoViewModel;
import com.example.model.Player;

import java.util.ArrayList;
import java.util.List;

public class RoomTwo extends AppCompatActivity {
    private Player player;
    private RoomTwoViewModel viewModel;
    private TextView scoreTextView;
    private Handler handler = new Handler();
    private List<ImageView> blackTilesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        // Retrieve the score value from the Intent
        Intent receiverIntent = getIntent();
        int score = receiverIntent.getIntExtra("score", 1000);
        player = (Player) receiverIntent.getSerializableExtra("player");
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
        RelativeLayout room2Layout = findViewById(R.id.room2Layout);
        Player player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomTwoViewModel(player, score);
        //KEYMOVEMENT
        blackTilesList = new ArrayList<>();
        room2Layout.setFocusableInTouchMode(true);

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
                    || (row == 1 && ((col == 0) || (col > 2 && col < 5)
                        || (col == 7) || (col == 9)))
                    || (row == 2 && (col == 2 || col == 4 || col == 9))
                    || (row == 3 && (col == 2 || col == 6 || (col > 7 && col < 10)))
                    || (row == 4 && (col == 3 || col == 8 || col == 10
                        || (col > 4 && col < 7)))
                    || (row == 5 && (col < 2))
                    || (row == 6 && ((col == 0) || (col > 2 && col < 5) || (col > 5 && col < 8)))
                    || (row == 7 && (((col > 6) && (col < 10))
                        || (col == 3) || col == 11))
                    || (row == 8 && ((col > 1 && col < 6) || col == 7))
                    || (row == 9 && (col == 2 || col == 4 || col > 9))
                    || (row == 11 && (col > 0))
                    || (row == 13 && col != 8))) {
                    tilesImageView.setImageResource(R.drawable.blacktile3);
                } else {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams = new
                        RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room2Layout.addView(tilesImageView, redTilesParams);
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

        Button room3btn = findViewById(R.id.room3btn);
        // Start updating the score
        room3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom3Activity(player);
            }
        });
    }
    private void startRoom3Activity(Player player) {
        Intent room3Intent = new Intent(this, RoomThree.class);
        room3Intent.putExtra("player", player);
        room3Intent.putExtra("score", viewModel.getScore());
        startActivity(room3Intent);
        finish(); // Finish the room2 activity
    }
    public void updateScore(int change) {
        viewModel.getScore();
    }
    public int getScore() {
        return viewModel.getScore();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        PlayerMovement playerMovement = new PlayerMovement();
        playerMovement.move(player, keyCode);

        // Updating player's pos and checking for collisions
        int newX = player.getX();
        int newY = player.getY();

        if (player.isValidMove(blackTilesList, newX, newY)) {
            // If the move is valid, update the player's position
            player.setX(newX);
            player.setY(newY);
            //updating avatars new pos
            ImageView avatarImageView = findViewById(R.id.imageAvatar);
            avatarImageView.setX(newX);
            avatarImageView.setY(newY);
        }

        return super.onKeyDown(keyCode, event);
    }

}

