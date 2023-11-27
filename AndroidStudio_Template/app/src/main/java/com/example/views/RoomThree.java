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
import com.example.model.Enemy;
import com.example.model.EnemyFactory;
import com.example.model.HealthPowerUp;
import com.example.model.PlayerMovement;
import com.example.model.PowerUp;
import com.example.model.ScorePowerUp;
import com.example.model.SkipRoomPowerUp;
import com.example.viewmodels.CollisionObserver;
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
    private EnemyFactory enemyFactory;
    private ImageView weaponImageView;
    private PlayerMovement playerMovement;
    private int whiteenemyX = 895;
    private int greenenemyX = 895;
    private int  whiteenemyY = 145;
    private int greenenemyY = 895;
    private PowerUp healthPowerUp;
    private PowerUp scorePowerUp;
    private PowerUp skipRoomPowerUp;
    private Handler h = new Handler();
    private CollisionObserver collisionObserver;
    private RelativeLayout room3Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);
        room3Layout = findViewById(R.id.room3Layout);
        // Retrieve values from the Intent
        Intent receiverIntent = getIntent();
        player = (Player) receiverIntent.getSerializableExtra("player");
        viewModel = new RoomThreeViewModel(player, receiverIntent.getIntExtra("score", 1000), this);
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
        enemyFactory = new EnemyFactory();       //enemy instantiation
        Enemy whiteEnemy = enemyFactory.createWhiteEnemy(this, whiteenemyX, whiteenemyY);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this, greenenemyX, greenenemyY);
        room3Layout.addView(whiteEnemy.getView());
        room3Layout.addView(greenEnemy.getView());
        //Instantiate power ups
        healthPowerUp = new HealthPowerUp(this, 920, 1000);
        scorePowerUp = new ScorePowerUp(this, 920, 360);
        skipRoomPowerUp = new SkipRoomPowerUp(this, 110, 100);
        room3Layout.addView(healthPowerUp.getView());
        room3Layout.addView(scorePowerUp.getView());
        room3Layout.addView(skipRoomPowerUp.getView());

        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 85;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);
        weaponImageView = findViewById(R.id.weaponImageView);
        weaponImageView.setImageResource(player.getWeaponResourceId());
        collisionObserver = new CollisionObserver(player, whiteEnemy, greenEnemy,
                healthPowerUp, scorePowerUp, skipRoomPowerUp);
        playerMovement = new PlayerMovement(blackTilesList, collisionObserver);
        playerMovement.setImageViews(avatarImageView, weaponImageView);
        // Start updating the score
        handler = new Handler();
        collisionObserver = new CollisionObserver(player, whiteEnemy, greenEnemy,
                healthPowerUp, scorePowerUp, skipRoomPowerUp);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                whiteEnemy.move();
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
                    //collisionObserver.enemyAttacked();
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
                int collision = collisionObserver.powerUpCollision();
                if (collision != -1) {
                    if (collision == 1) {
                        player.setHealthPoints(player.getHealthPoints() + 20);
                        healthPowerUp.getView().setVisibility(View.INVISIBLE);
                        healthPointsTextView.setText("Health Points: " + player.getHealthPoints());
                    } else if (collision == 2) {
                        viewModel.updateScore(10);
                        scorePowerUp.getView().setVisibility(View.INVISIBLE);
                        scoreTextView.setText("Score: " + viewModel.getScore());
                    } else if (collision == 3) {
                        skipRoomPowerUp.getView().setVisibility(View.INVISIBLE);
                        viewModel.moveToNextRoom();
                    }
                }
                viewModel.updateScore(0);
                scoreTextView.setText("Score: " + viewModel.getScore());
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void updateWeaponPosition(int keyCode) {
        if (avatarImageView != null && weaponImageView != null && room3Layout != null) {
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

            Log.d("RoomThree", "Player X: " + playerLocation[0]);
            Log.d("RoomThree", "Player Y: " + playerLocation[1]);
            Log.d("RoomThree", "Weapon X: " + weaponLocation[0]);
            Log.d("RoomThree", "Weapon Y: " + weaponLocation[1]);
        }
    }

    private void restartActivity() {
        recreate(); // restart
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView);
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            playerMovement.initiateAttack();
        }
        updateWeaponPosition(keyCode);
        if (viewModel.checkReachedGoal()) {
            viewModel.moveToNextRoom();
        }
        return super.onKeyDown(keyCode, event);
    }
    public ImageView getWeaponImageView() {
        return weaponImageView;
    }
}
