package com.example.views;


import android.content.Intent;
import android.media.MediaPlayer;
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
import com.example.model.HealthPowerUp;
import com.example.model.PlayerMovement;
import com.example.model.PowerUp;
import com.example.model.ScorePowerUp;
import com.example.model.SkipRoomPowerUp;
import com.example.model.KeyPowerUp;
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
    private PowerUp healthPowerUp;
    private PowerUp scorePowerUp;
    private PowerUp skipRoomPowerUp;
    private PowerUp keyPowerUp;
    private Handler h = new Handler();
    private CollisionObserver collisionObserver;
    private RelativeLayout room2Layout;
    private PlayerMovement playerMovement;
    private ImageView skullImageView;
    private boolean hasKey = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        room2Layout = findViewById(R.id.room2Layout);
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomTwoViewModel(player, receiverIntent.getIntExtra("score", 1000), this);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + viewModel.getScore());
        TextView playerNameTextView = findViewById(R.id.playerNameTextView);
        TextView healthPointsTextView = findViewById(R.id.healthPointsTextView);
        playerNameTextView.setText("Player Name: " + player.getPlayerName());
        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
        blackTilesList = new ArrayList<>();
        room2Layout.setFocusableInTouchMode(true);
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
        enemyFactory = new EnemyFactory();     //enemy instantiation
        Enemy yellowEnemy = enemyFactory.createYellowEnemy(this, yellowenemyX, yellowenemyY);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this, greenenemyX, greenenemyY);
        room2Layout.addView(yellowEnemy.getView());
        room2Layout.addView(greenEnemy.getView());
        //Instantiate power ups
        healthPowerUp = new HealthPowerUp(this, 110, 100);
        scorePowerUp = new ScorePowerUp(this, 380, 650); // 1000, 725
        skipRoomPowerUp = new SkipRoomPowerUp(this, 1010, 100); //500, 900
        keyPowerUp = new KeyPowerUp(this, 300, 300);
        room2Layout.addView(healthPowerUp.getView());
        room2Layout.addView(scorePowerUp.getView());
        room2Layout.addView(skipRoomPowerUp.getView());
        room2Layout.addView(keyPowerUp.getView());
        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 715;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);
        weaponImageView = findViewById(R.id.weaponImageView);
        weaponImageView.setImageResource(player.getWeaponResourceId());
        collisionObserver = new CollisionObserver(player, yellowEnemy, greenEnemy,
                healthPowerUp, scorePowerUp, skipRoomPowerUp, keyPowerUp);
        playerMovement = new PlayerMovement(blackTilesList, collisionObserver);
        playerMovement.setImageViews(avatarImageView, weaponImageView);
        // Start updating the score
        handler = new Handler();
        MediaPlayer collisionSound = MediaPlayer.create(this, R.raw.collision);
        MediaPlayer powerupSound = MediaPlayer.create(this, R.raw.powerup);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                yellowEnemy.move();
                greenEnemy.move();
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
                    //collisionObserver.enemyAttacked();
                    viewModel.updateScore(-10); //decrement score by 10 each time HP is decremented
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
                int collision = collisionObserver.powerUpCollision();
                if (collision != -1) {
                    if (collision == 1) {
                        player.setHealthPoints(player.getHealthPoints() + 20);
                        healthPowerUp.getView().setVisibility(View.INVISIBLE);
                        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
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
        }, 1000);
    }
    private void updateWeaponPosition(int keyCode) {
        if (avatarImageView != null && weaponImageView != null && room2Layout != null) {
            Log.d("RoomTwo", "Updating weapon position");

            int weaponSpeed = 10; // Adjust this value as needed

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
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            playerMovement.initiateAttack();
        }
        updateWeaponPosition(keyCode);
        if (viewModel.checkReachedGoal() && hasKey) {
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
    public ImageView getWeaponImageView() {
        return weaponImageView;
    }


}