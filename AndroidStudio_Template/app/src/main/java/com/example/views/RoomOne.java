package com.example.views;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.model.EnemyFactory;
import com.example.model.Enemy;
import com.example.model.Player;
import com.example.demo_2340.R;
import com.example.viewmodels.RoomOneViewModel;
import java.util.ArrayList;
import java.util.List;


import android.view.KeyEvent;



public class RoomOne extends AppCompatActivity {
    private RoomOneViewModel viewModel;
    // Initial Score and Handler
    private Player player;
    private EnemyFactory enemyFactory;
    private TextView scoreTextView;
    private Handler handler;
    private ImageView avatarImageView;
    private List<ImageView> blackTilesList; //contains ref of black tiles aka collisions/walls
    //ImageView blueenemy, whiteenemy;

    private Handler h = new Handler();

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


        //KEYMOVEMENT
        blackTilesList = new ArrayList<>();
        room1Layout.setFocusableInTouchMode(true);
        // this line causes error when moving to next room
        //player.addObserver(viewModel);
        player.setGoalX(715);
        player.setGoalY(5);

        viewModel = new RoomOneViewModel(player, this);

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
                }
                else {
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
        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 445;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);
        scoreTextView = findViewById(R.id.scoreTextView);
        //instantiating enemy factory
        enemyFactory = new EnemyFactory();
        Enemy blueEnemy = enemyFactory.createBlueEnemy(this, 715, 65);
        Enemy whiteEnemy = enemyFactory.createWhiteEnemy(this, 500, 60);

        room1Layout.addView(blueEnemy.getView());
        room1Layout.addView(whiteEnemy.getView());

        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                blueEnemy.move();
                whiteEnemy.move();

                viewModel.updateScore(-1);
                scoreTextView.setText("Score: " + viewModel.getScore());
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    public int getScore() {
        return viewModel.getScore();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView)) {
            if (viewModel.checkReachedGoal()) {
                viewModel.moveToNextRoom();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //ACCESSOR METHODS
    private Handler getHandler() {
        return h;
    }


}
