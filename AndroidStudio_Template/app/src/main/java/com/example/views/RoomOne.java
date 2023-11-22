package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.model.EnemyFactory;
import com.example.model.Enemy;
import com.example.model.Player;
import com.example.demo_2340.R;
import com.example.model.PlayerMovement;
import com.example.viewmodels.CollisionObserver;
import com.example.viewmodels.RoomOneViewModel;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import android.view.KeyEvent;



public class RoomOne extends AppCompatActivity {
    private RoomOneViewModel viewModel;
    // Initial Score and Handler
    private Player player;
    private EnemyFactory enemyFactory;
    private Enemy blueEnemy;
    private Enemy whiteEnemy;
    private TextView scoreTextView;
    private Handler handler;
    private ImageView avatarImageView;
    private ImageView weaponImageView;
    private List<ImageView> blackTilesList; //contains ref of black tiles aka collisions/walls
    private Handler h = new Handler();
    private CollisionObserver collisionObserver;
    private PlayerMovement playerMovement;
    private  RelativeLayout room1Layout;
    private static final String TAG = "RoomOne";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        Log.d(TAG, "onCreate");
        room1Layout = findViewById(R.id.room1Layout);
        // Retrieve values from the Intent
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomOneViewModel(player, this);
        // Initialize Score Display (update handled in Runnable)
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + viewModel.getScore());
        // Initialize Bottom Display
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView healthPointsTextView = findViewById(R.id.healthPointsTextView);
        playerNameTextView.setText("Player Name: " + player.getPlayerName());
        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());

        blackTilesList = new ArrayList<>();
        room1Layout.setFocusableInTouchMode(true);

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
                        || (row == 9 && ((col > 2) && (col < 5) || col == 6
                        || col == 8 || col == 11))
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

        //INSTANTIATION
        //instantiating enemy factory
        enemyFactory = new EnemyFactory();
        blueEnemy = enemyFactory.createBlueEnemy(this, 715, 65);
        whiteEnemy = enemyFactory.createWhiteEnemy(this,  500, 60);

        room1Layout.addView(blueEnemy.getView());
        room1Layout.addView(whiteEnemy.getView());


        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());

        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 445;

        avatarImageView.setLayoutParams(playerLayout);

        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);


        weaponImageView = findViewById(R.id.weaponImageView);
        weaponImageView.setImageResource(player.getWeaponResourceId());


        collisionObserver = new CollisionObserver(player, blueEnemy, whiteEnemy);
        playerMovement = new PlayerMovement(blackTilesList, collisionObserver);
        playerMovement.setImageViews(avatarImageView, weaponImageView);


        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Score update loop");
                if (blueEnemy != null && whiteEnemy != null) {
                    blueEnemy.move();
                    whiteEnemy.move();

                    if (collisionObserver.enemyCollision()) {
                        Log.d(TAG, "Enemy collision detected");
                        if (player.getDifficulty() == 1.00) {
                            player.setHealthPoints(player.getHealthPoints() - 25);
                        } else if (player.getDifficulty() == 0.75) {
                            player.setHealthPoints(player.getHealthPoints() - 15);
                        } else {
                            player.setHealthPoints(player.getHealthPoints() - 10);
                        }
                        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
                        collisionObserver.enemyAttacked();
                        /**
                         * Automatically navigate to the game over screen if
                         * player health (HP) reaches 0 (i.e the player dies)
                         */
                        if (player.getHealthPoints() == 0) {
                            Log.d(TAG, "Player health reached 0");
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
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateWeaponPosition(int keyCode) {
        if (avatarImageView != null && weaponImageView != null && room1Layout != null) {
            Log.d("RoomOne", "Updating weapon position");

            int weaponSpeed = 11; // Adjust this value as needed
            int weaponWidth = weaponImageView.getWidth();
            int weaponHeight = weaponImageView.getHeight();

            weaponImageView.clearAnimation();

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

            Log.d("RoomOne", "Player X: " + playerLocation[0]);
            Log.d("RoomOne", "Player Y: " + playerLocation[1]);
            Log.d("RoomOne", "Weapon X: " + weaponLocation[0]);
            Log.d("RoomOne", "Weapon Y: " + weaponLocation[1]);
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
        Log.d(TAG, "onKeyDown called. KeyCode: " + keyCode);
        if (viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView)) {
            updateWeaponPosition(keyCode);
            if (viewModel.checkReachedGoal()) {
                Log.d(TAG, "Player reached goal, moving to the next room");
                viewModel.moveToNextRoom();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //ACCESSOR METHODS
    public Handler getHandler() {
        return h;
    }
    public Enemy getBlueEnemy() {
        return blueEnemy;
    }

    public Player getPlayer() {
        return player;
    }
    public CollisionObserver getCollisionObserver() {
        return collisionObserver;
    }
}

