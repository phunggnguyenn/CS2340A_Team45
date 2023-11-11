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
    private List<ImageView> blackTilesList;
    private int greenenemyX = 85;
    private int yellowenemyX = 700;
    private int greenenemyY = 750;
    private int yellowenemyY = 750;
    private Handler h = new Handler();
    private CollisionObserver collisionObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        RelativeLayout room2Layout = findViewById(R.id.room2Layout);
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
        Enemy yellowEnemy = enemyFactory.createYellowEnemy(this);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this);

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
                }

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
        viewModel.handleKeyEvent(keyCode, blackTilesList, avatarImageView);
        if (viewModel.checkReachedGoal()) {
            viewModel.moveToNextRoom();
        }
        return super.onKeyDown(keyCode, event);
    }

    //ACCESSOR METHODS
    private int getGreenenemyX() {
        return greenenemyX;
    }
    private int getGreenenemyY() {
        return greenenemyY;
    }
    private int getYellowenemyX() {
        return yellowenemyX;
    }
    private int getYellowenemyY() {
        return yellowenemyY;
    }
    private Handler getHandler() {
        return h;
    }


}
