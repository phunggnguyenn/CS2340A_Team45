package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.demo_2340.R;
import com.example.model.EnemyFactory;
import com.example.model.Enemy;
import com.example.viewmodels.CollisionObserver;
import com.example.viewmodels.RoomTwoViewModel;
import com.example.model.Player;
import java.util.ArrayList;
import java.util.List;

public class RoomTwo extends AppCompatActivity {
    private RoomTwoViewModel viewModel;
    private Player player;
    private EnemyFactory enemyFactory;
    private TextView scoreTextView;
    private Handler handler;
    private ImageView avatarImageView;
    private ImageView weaponImageView;
    private List<ImageView> blackTilesList;
    private int greenenemyX = 85;
    private int yellowenemyX = 700;
    private int greenenemyY = 750;
    private int yellowenemyY = 750;
    private Handler h = new Handler();
    private CollisionObserver collisionObserver;
    private RelativeLayout room2Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        room2Layout = findViewById(R.id.room2Layout);
        // Retrieve values from the Intent
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomTwoViewModel(player, receiverIntent.getIntExtra("score", 1000), this);
        // Initialize Score Display (update handled in Runnable)
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + viewModel.getScore());
        // TextViews (display name and HP)
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView healthPointsTextView = findViewById(R.id.healthPointsTextView);
        playerNameTextView.setText("Player Name: " + player.getPlayerName());
        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());




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
                        || (row == 6 && ((col == 0) || (col > 2 && col < 5)
                        || (col > 5 && col < 8)))
                        || (row == 7 && (((col > 6) && (col < 10))
                        || (col == 3) || col == 11))
                        || (row == 8 && ((col > 1 && col < 6) || col == 7))
                        || (row == 9 && (col == 2 || col == 4 || col > 9))
                        || (row == 11 && (col > 0))
                        || (row == 13 && col != 8))) {
                    tilesImageView.setImageResource(R.drawable.blacktile3);
                    blackTilesList.add(tilesImageView);
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
        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 715;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);


        //enemy instantiationn
        enemyFactory = new EnemyFactory();
        Enemy yellowEnemy = enemyFactory.createYellowEnemy(this, yellowenemyX, yellowenemyY);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this, greenenemyX, greenenemyY);

        room2Layout.addView(yellowEnemy.getView());
        room2Layout.addView(greenEnemy.getView());

        collisionObserver = new CollisionObserver(player, yellowEnemy, greenEnemy);
        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                yellowEnemy.move();
                greenEnemy.move();
                if (collisionObserver.enemyCollision()) {
                    if (player.getDifficulty() == 1.00) {
                        player.setHealthPoints(player.getHealthPoints() - 25);
                    } else if (player.getDifficulty() == 0.75) {
                        player.setHealthPoints(player.getHealthPoints() - 15);
                    } else {
                        player.setHealthPoints(player.getHealthPoints() - 10);
                    }
                    healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
                    /**
                     * Automatically navigate to the game over screen if
                     * player health (HP) reaches 0 (i.e the player dies)
                     */
                    if (player.getHealthPoints() == 0) {
                        setContentView(R.layout.activity_game_end_lose);
                        Button restart = findViewById(R.id.restart);
                        restart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                restartActivity();
                            }
                        });
                    }
                }

                viewModel.updateScore(-1);
                scoreTextView.setText("Score: " + viewModel.getScore());
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    private void updateWeaponPosition(int keyCode) {
        if (avatarImageView != null && weaponImageView != null && room2Layout != null) {
            Log.d("RoomOne", "Updating weapon position");

            int weaponSpeed = 15; // Adjust this value as needed
            int weaponWidth = weaponImageView.getWidth();
            int weaponHeight = weaponImageView.getHeight();

            int[] playerLocation = new int[2];
            avatarImageView.getLocationOnScreen(playerLocation);

            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    weaponImageView.offsetTopAndBottom(-weaponSpeed);
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    weaponImageView.offsetTopAndBottom(weaponSpeed);
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    weaponImageView.offsetLeftAndRight(-weaponSpeed);
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    weaponImageView.offsetLeftAndRight(weaponSpeed);
                    break;
                default:
                    break;
            }

            int[] weaponLocation = new int[2];
            weaponImageView.getLocationOnScreen(weaponLocation);

            Log.d("RoomTwo", "Player X: " + playerLocation[0]);
            Log.d("RoomTwo", "Player Y: " + playerLocation[1]);
            Log.d("RoomTwo", "Weapon X: " + weaponLocation[0]);
            Log.d("RoomTwo", "Weapon Y: " + weaponLocation[1]);
        }
    }
    private void restartActivity() {
        recreate(); // restart
        finish();
    }
    public int getScore() {
        return viewModel.getScore();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView);
        updateWeaponPosition(keyCode);
        if (viewModel.checkReachedGoal()) {
            viewModel.moveToNextRoom();
        }
        return super.onKeyDown(keyCode, event);
    }

    //ACCESSOR METHODS
    public int getGreenenemyX() {
        return greenenemyX;
    }
    private int getGreenenemyY() {
        return greenenemyY;
    }
    public int getYellowenemyX() {
        return yellowenemyX;
    }
    private int getYellowenemyY() {
        return yellowenemyY;
    }
    private Handler getHandler() {
        return h;
    }


}