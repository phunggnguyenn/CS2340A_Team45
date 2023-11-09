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
import com.example.model.Enemy;
import com.example.model.EnemyFactory;
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
    private int whiteenemyX = 895;
    private int greenenemyX = 895;
    private int  whiteenemyY = 145;
    private int greenenemyY = 895;
    private Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);
        RelativeLayout room3Layout = findViewById(R.id.room3Layout);
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
        avatarImageView = findViewById(R.id.imageAvatar);
        avatarImageView.setImageResource(player.getAvatarId());
        ViewGroup.MarginLayoutParams playerLayout = (ViewGroup.MarginLayoutParams)
                avatarImageView.getLayoutParams();
        playerLayout.topMargin = 1165;
        playerLayout.leftMargin = 85;
        avatarImageView.setLayoutParams(playerLayout);
        player.setX(playerLayout.leftMargin);
        player.setY(playerLayout.topMargin);


        //enemy instantiation
        enemyFactory = new EnemyFactory();
        Enemy whiteEnemy = enemyFactory.createWhiteEnemy(this, whiteenemyX, whiteenemyY);
        Enemy greenEnemy = enemyFactory.createGreenEnemy(this, greenenemyX, greenenemyY);

        room3Layout.addView(whiteEnemy.getView());
        room3Layout.addView(greenEnemy.getView());
        // Start updating the score
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                whiteEnemy.move();
                greenEnemy.move();

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

}
