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
import com.example.viewmodels.RoomTwoViewModel;
import com.example.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomTwo extends AppCompatActivity {
    private RoomTwoViewModel viewModel;
    private Player player;
    private EnemyFactory enemyFactory;
    private TextView scoreTextView;
    private Handler handler;
    private ImageView avatarImageView;
    private List<ImageView> blackTilesList;
    ImageView greenenemy, yellowenemy;
    int greenenemy_x = 85, yellowenemy_x = 700;
    int greenenemy_y = 750, yellowenemy_y = 750;
    Handler h = new Handler();
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
        viewModel = new RoomTwoViewModel(player, score, this);
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
        player.setGoalX(85);
        player.setGoalY(5);

        //enemy instantiationn
        enemyFactory = new EnemyFactory();
        Enemy yellowEnemy = enemyFactory.createYellowEnemy(this, yellowenemy_x, yellowenemy_y);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this, greenenemy_x, greenenemy_y);

        room2Layout.addView(yellowEnemy.getView());
        room2Layout.addView(greenEnemy.getView());
        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                yellowEnemy.move();
                greenEnemy.move();

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


}
