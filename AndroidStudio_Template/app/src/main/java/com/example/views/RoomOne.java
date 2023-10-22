package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.model.Player;
import com.example.demo_2340.R;
import com.example.model.PlayerMovement;
import com.example.viewmodels.RoomOneViewModel;

import java.util.ArrayList;
import java.util.List;
import android.view.KeyEvent;


public class RoomOne extends AppCompatActivity {
    private RoomOneViewModel viewModel;
    // Initial Score and Handler
    private Player player;
    private TextView scoreTextView;
    private Handler handler;

    private List<ImageView> blackTilesList; //contains ref of black tiles aka collisions/walls


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        RelativeLayout room1Layout = findViewById(R.id.room1Layout);
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView healthPointsTextView = findViewById(R.id.healthPointsTextView);
        playerNameTextView.setText("Player Name: " + player.getPlayerName());
        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
        viewModel = new RoomOneViewModel(player, this);
        //KEYMOVEMENT
        blackTilesList = new ArrayList<>();
        room1Layout.setFocusableInTouchMode(true);
        // this line causes error when moving to next room
        //player.addObserver(viewModel);
        player.setGoalX(715);
        player.setGoalY(5);

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
                if ((row == 0 && ((col < 8) || (col > 8)))
                    || (row == 1 && col < 1)
                    || (row == 2 && ((col < 2) || (col > 4 && col < 11)))
                    || (row == 3 && col == 9)
                    || (row == 4 && (col < 8))
                    || (row == 5 && ((col == 1) || (col == 3)
                        || (col == 6) || (col > 8 && col < 11)))
                    || (row == 6 && ((col == 6) || (col == 10)))
                    || (row == 7 && (((col > 2) && (col < 5)) || (col == 10)))
                    || (row == 8 && ((col > 1 && col < 4) || (col > 5 && col < 9)))
                    || (row == 9 && ((col > 2) && (col < 5) || col == 6 || col == 8 || col == 11))
                    || (row == 10 && col == 11)
                    || (row == 11 && ((col > 0 && col < 4) || (col > 4 && col < 8)))
                    || (row == 13 && col != 5)) {
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

                room1Layout.addView(tilesImageView, redTilesParams);
            }

        }
        ImageView avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams) avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 445;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);

        Button room2btn = findViewById(R.id.room2btn);
        scoreTextView = findViewById(R.id.scoreTextView);

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
        room2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom2Activity(player);
            }
        });


    }

    private void startRoom2Activity(Player player) {
        Intent room2Intent = new Intent(this, RoomTwo.class);
        room2Intent.putExtra("player", player);
        room2Intent.putExtra("score", viewModel.getScore());
        startActivity(room2Intent);
        finish(); // Finish the room1 activity
    }

    public int getScore() {
        return viewModel.getScore();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int oldX = player.getX();
        int oldY = player.getY();

        player.move(keyCode);

        // Updating player's pos and checking for collisions
        int newX = player.getX();
        int newY = player.getY();

        if (player.isValidMove(blackTilesList, newX, newY)) {
            Log.d("RoomOne", "Player position: x=" + newX + ", y=" + newY);
            Log.d("RoomOne", "Goal position: x=" + player.getGoalX() + ", y=" + player.getGoalY());
            // If the move is valid, update the player's pos
            player.setX(newX);
            player.setY(newY);
            //updating avatars new pos
            ImageView avatarImageView = findViewById(R.id.imageAvatar);
            ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams) avatarImageView.getLayoutParams();
            playerLayout.topMargin = newY; // Update top margin
            playerLayout.leftMargin = newX; // Update left margin
            avatarImageView.setLayoutParams(playerLayout);
            if (newX == player.getGoalX() && newY == player.getGoalY()) {
                //player.notifyObservers();
                startRoom2Activity(player);
            }
        } else {
            player.setX(oldX);
            player.setY(oldY);
        }

        return super.onKeyDown(keyCode, event);
    }
}

