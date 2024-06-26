package com.example.views;


import android.content.Intent;

import android.media.MediaPlayer;
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
import com.example.model.HealthPowerUp;
import com.example.model.Player;
import com.example.demo_2340.R;
import com.example.model.PlayerMovement;
import com.example.model.PowerUp;
import com.example.model.ScorePowerUp;
import com.example.model.SkipRoomPowerUp;

import com.example.model.KeyPowerUp;
import com.example.viewmodels.CollisionObserver;
import com.example.viewmodels.RoomOneViewModel;

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
    private PowerUp healthPowerUp;
    private PowerUp scorePowerUp;
    private PowerUp skipRoomPowerUp;
    private PowerUp keyPowerUp;
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
    private boolean hasKey = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        room1Layout = findViewById(R.id.room1Layout);
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomOneViewModel(player, this);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + viewModel.getScore());
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView healthPointsTextView = findViewById(R.id.healthPointsTextView);
        playerNameTextView.setText("Player Name: " + player.getPlayerName());
        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
        blackTilesList = new ArrayList<>();
        room1Layout.setFocusableInTouchMode(true);
        int tileWidth = 80;
        int tileHeight = 80;
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
        enemyFactory = new EnemyFactory();         //instantiating enemy factory
        blueEnemy = enemyFactory.createBlueEnemy(this, 715, 65);
        whiteEnemy = enemyFactory.createWhiteEnemy(this,  500, 60);
        room1Layout.addView(blueEnemy.getView());
        room1Layout.addView(whiteEnemy.getView());
        healthPowerUp = new HealthPowerUp(this, 20, 460);      //Instantiate power ups
        scorePowerUp = new ScorePowerUp(this, 1010, 725);
        skipRoomPowerUp = new SkipRoomPowerUp(this, 100, 90);
        keyPowerUp = new KeyPowerUp(this, 900, 300);
        room1Layout.addView(healthPowerUp.getView());
        room1Layout.addView(scorePowerUp.getView());
        room1Layout.addView(skipRoomPowerUp.getView());
        room1Layout.addView(keyPowerUp.getView());
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
        collisionObserver = new CollisionObserver(player, blueEnemy, whiteEnemy, healthPowerUp,
                scorePowerUp, skipRoomPowerUp, keyPowerUp);
        playerMovement = new PlayerMovement(blackTilesList, collisionObserver);
        playerMovement.setCollisionObserver(collisionObserver);
        playerMovement.setImageViews(avatarImageView, weaponImageView);
        // Start updating the score
        handler = new Handler();
        MediaPlayer collisionSound = MediaPlayer.create(this, R.raw.collision);
        MediaPlayer powerupSound = MediaPlayer.create(this, R.raw.powerup);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (blueEnemy != null && whiteEnemy != null) {
                    blueEnemy.move();
                    whiteEnemy.move();
                    if (collisionObserver.enemyCollision()) {
                        if (player.getDifficulty() == 1.00) {
                            player.setHealthPoints(player.getHealthPoints() - 25);
                            collisionSound.start();
                        } else if (player.getDifficulty() == 0.75) {
                            player.setHealthPoints(player.getHealthPoints() - 15);
                            collisionSound.start();
                        } else {
                            player.setHealthPoints(player.getHealthPoints() - 10);
                            collisionSound.start();
                        }
                        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
                        viewModel.updateScore(-10); //decrement score by same amount of HP affected.
                        // Automatically navigate to the game over screen if
                        // player health (HP) reaches 0 (i.e the player dies)
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
                    int collision = collisionObserver.powerUpCollision();
                    if (collision != -1) {
                        if (collision == 1) {
                            player.setHealthPoints(player.getHealthPoints() + 20);
                            healthPowerUp.getView().setVisibility(View.INVISIBLE);
                            healthPointsTextView.setText("Health Points: "
                                    + player.getHealthPoints());
                            powerupSound.start();
                        } else if (collision == 2) {
                            viewModel.updateScore(10);
                            scorePowerUp.getView().setVisibility(View.INVISIBLE);
                            scoreTextView.setText("Score: " + viewModel.getScore());
                            powerupSound.start();
                        } else if (collision == 3) {
                            skipRoomPowerUp.getView().setVisibility(View.INVISIBLE);
                            viewModel.moveToNextRoom();
                            powerupSound.start();
                        } else if (collision == 4) {
                            keyPowerUp.getView().setVisibility(View.INVISIBLE);
                            playerNameTextView.setText("Player Name: " + player.getPlayerName()
                                    + " Has Key!!");
                            hasKey = true;
                        }
                    }
                    viewModel.updateScore(0);
                    scoreTextView.setText("Score: " + viewModel.getScore());
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    private void updateWeaponPosition(int keyCode) {
        if (avatarImageView != null && weaponImageView != null && room1Layout != null) {
            Log.d("RoomOne", "Updating weapon position");

            int weaponSpeed = 11; // Adjust this value as needed

            weaponImageView.clearAnimation();

            int[] playerLocation = new int[2];
            avatarImageView.getLocationOnScreen(playerLocation);

            switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                weaponImageView.setY(weaponImageView.getY() - weaponSpeed);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                weaponImageView.setY(weaponImageView.getY() + weaponSpeed);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                weaponImageView.setX(weaponImageView.getX() - weaponSpeed);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                weaponImageView.setX(weaponImageView.getX() + weaponSpeed);
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
            if (keyCode == KeyEvent.KEYCODE_SPACE) {
                playerMovement.initiateAttack();
                viewModel.enemyDestroyed(); // add 50 to score when the player destroys enemies.
            }
            updateWeaponPosition(keyCode);
            if (viewModel.checkReachedGoal() && hasKey) {
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
    public ImageView getWeaponImageView() {
        return weaponImageView;
    }
}
