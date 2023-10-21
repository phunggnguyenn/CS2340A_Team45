package com.example.demo_2340;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.views.GameEndActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private PlayerView playerView;
    private float playerX;
    private float playerY;
    private List<Dot> dots = new ArrayList<>();
    private Random random = new Random();
    private RelativeLayout gameLayout;
    private int screenWidth;
    private int screenHeight;
    private Map<Dot, DotView> dotViewMap = new HashMap<>();
    private Timer dotTimer;
    private static final int MAX_DOTS = 20;
    private TextView dotCountTextView;
    private int dotCount = 0;
    private double difficulty;
    private int dotsToWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.gameLayout);
        dotCountTextView = findViewById(R.id.dotCountTextView);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        // Spawn player in middle of screen
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;


        // Get difficulty selected from Main screen.
        difficulty = getIntent().getDoubleExtra("difficulty", 1);
        // 50 for easy, 75 for med, 100 for hard to win.
        dotsToWin = (int) (100 * difficulty);
        // Create red dot
        playerView = new PlayerView(this, playerX, playerY, 50);
        gameLayout.addView(playerView);
        // Create dot list
        initializeDots();
        // Draw dots on screen
        drawDots();

        /*
        Timer to call checkCollisions every .5 seconds to determine if dots have expired yet.
         */
        dotTimer = new Timer();
        dotTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        checkCollisions();
                        respawnDotsIfNeeded();
                    }
                });
            }
        }, 0, 500); // Check every .5 seconds
    }

    // Handle key events to move the player
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO logic to move the player (remember to check collisions)
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_DOWN:
            playerY -= 10;
            checkCollisions();
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            playerY += 10;
            checkCollisions();
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            playerX -= 10;
            checkCollisions();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            playerX += 10;
            checkCollisions();
            break;
        default:
            break;
        }
        return true;
    }

    private void initializeDots() {
        // TODO Create and add dots with random positions
        for (int i = 0; i < MAX_DOTS; i++) {
            float randomX = random.nextInt(screenWidth);
            float randomY = random.nextInt(screenHeight);
            int radius = 20;
            Dot dot = new Dot(randomX, randomY, radius);
            dots.add(dot);
        }
    }

    /*
    Method to create dot objects. Maps a dot object to a specific dotView.
     */
    private void drawDots() {
        for (Dot dot : dots) {
            DotView newDot = new DotView(this, dot);
            gameLayout.addView(newDot);
            dotViewMap.put(dot, newDot);
        }
    }

    /** @noinspection checkstyle:TodoComment*/
    // Maintains 20 dots on screen
    private void respawnDotsIfNeeded() {
        while (dots.size() < MAX_DOTS) {
            respawnDot();
        }
    }

    /** @noinspection checkstyle:TodoComment*/ // Recreates the dots. Respawn mechanic
    private void respawnDot() {
        float randomX = random.nextInt(screenWidth);
        float randomY = random.nextInt(screenHeight);
        Dot dot = new Dot(randomX, randomY, 20);
        dots.add(dot);
        DotView newDot = new DotView(this, dot);
        gameLayout.addView(newDot);
        dotViewMap.put(dot, newDot);
    }

    /** @noinspection checkstyle:TodoComment, checkstyle:TodoComment */ /*
    Method that checks to see if any collision has occurred.
     */
    private void checkCollisions() {
        for (int i = 0; i < dots.size(); i++) {
            Dot dot = dots.get(i);
            if (dot.isVisible() && isCollision(playerView, dot)) {
                dot.setInvisible();
                gameLayout.removeView(dotViewMap.get(dot));
                dots.remove(i);
                dotCount++;

                dotCountTextView.setText("Dots Collected: " + dotCount);
                if (dotCount >= dotsToWin) {
                    launchGameWinActivity();
                }
            } else if (dot.isExpired()) {  //checks if dots expired
                System.out.println("Dot has expired: " + dot);
            }
        }
    }

    /*
    Method that has logic to detect collisions.
    */
    private boolean isCollision(PlayerView playerView, Dot dot) {
        float playerX = playerView.getX();
        float playerY = playerView.getY();
        int playerRadius = playerView.getRadius();
        float dotX = dot.getX();
        float dotY = dot.getY();
        int dotRadius = dot.getRadius();

        /*
        Creates a rectangle around dot, and checks for an intersection between
        player rect and dot rect. Intersection = collision.
         */
        RectF playerRect = new RectF(playerX - playerRadius,
                playerY - playerRadius, playerX + playerRadius,
                playerY + playerRadius);
        RectF dotRect = new RectF(dotX - dotRadius, dotY - dotRadius,
                dotX + dotRadius, dotY + dotRadius);

        return playerRect.intersect(dotRect);
    }

    // Changes game screen to GameWinActivity
    private void launchGameWinActivity() {
        Intent intent = new Intent(this, GameEndActivity.class);
        startActivity(intent);
        finish();
    }

    //ACCESSOR METHODS - for checkstyle
    public RelativeLayout getGameLayout() {
        return gameLayout;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }


}
