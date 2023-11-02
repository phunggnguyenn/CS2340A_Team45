package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.demo_2340.R;
//import com.example.model.BlueGen;
import com.example.model.Enemies;
import com.example.model.Enemy;
//import com.example.model.GreenGen;
//import com.example.model.WhiteGen;
import com.example.viewmodels.RoomThreeViewModel;
import com.example.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomThree extends AppCompatActivity {
    private Player player;
    private RoomThreeViewModel viewModel;
    private TextView scoreTextView;
    private Handler handler = new Handler();
    private ImageView avatarImageView;
    private List<ImageView> blackTilesList;
    ImageView blueenemy, whiteenemy, greenenemy;
    int blueenemy_x = 895, whiteenemy_x = 895, greenenemy_x = 895;
    int blueenemy_y = 65, whiteenemy_y = 145, greenenemy_y = 895;
    Handler h = new Handler();
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
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 85;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);
        player.setGoalX(895);
        player.setGoalY(5);
        // Instantiate ImageViews for enemy
        blueenemy = (ImageView) findViewById(R.id.imageBlueEnemy);
        whiteenemy = (ImageView) findViewById(R.id.imageWhiteEnemy);
        greenenemy = (ImageView) findViewById(R.id.imageGreenEnemy);
        // Set the X and Y coordinate of enemy
        blueenemy.setX(blueenemy_x);
        blueenemy.setY(blueenemy_y);
        whiteenemy.setX(whiteenemy_x);
        whiteenemy.setY(whiteenemy_y);
        greenenemy.setX(greenenemy_x);
        greenenemy.setY(greenenemy_y);
        // Create method that will keep enemies moving
        move();
        /**
        Enemies whiteGen = new WhiteGen();
        Enemy white = whiteGen.generateEnemy();
        Enemies blueGen = new BlueGen();
        Enemy blue = blueGen.generateEnemy();
        Enemies greenGen = new GreenGen();
        Enemy green = greenGen.generateEnemy();
         */
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
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView);
        if (viewModel.checkReachedGoal()) {
            viewModel.moveToNextRoom();
        }
        return super.onKeyDown(keyCode, event);
    }
    // Start enemy movement
    public void move() {
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                h.postDelayed(this, 100);
                // Call function for enemy movement
                moveEnemy();
            }
        }, 1000);
    }
    public void moveEnemy() {
        // white enemy
        whiteenemy_x = whiteenemy_x - 10;
        whiteenemy.setX(whiteenemy_x);
        whiteenemy.setY(whiteenemy_y);
        if(whiteenemy_x == 0) {
            whiteenemy_x = 895;
        }
        // blue enemy
        blueenemy_x = blueenemy_x - 10;
        blueenemy.setX(blueenemy_x);
        blueenemy.setY(blueenemy_y);
        if(blueenemy_x == 0) {
            blueenemy_x = 895;
        }
        // green enemy
        greenenemy_x = greenenemy_x - 10;
        greenenemy.setX(greenenemy_x);
        greenenemy.setY(greenenemy_y);
        if(greenenemy_x == 0) {
            greenenemy_x = 895;
        }
    }
}
