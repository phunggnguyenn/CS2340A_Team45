package com.example.model;

import java.io.Serializable;
import android.view.KeyEvent;
import android.widget.ImageView;
import java.util.List;
import com.example.demo_2340.R;
import android.util.Log;

public class Player implements Serializable {
    private int x; //for movement
    private int y; //for movement
    private int playerWidth;
    private int playerHeight;
    private String playerName;
    private int healthPoints;
    private int avatarId;
    private static Player player;
    private int goalX;
    private int goalY;
    private double difficulty;
    private Weapon weapon;


    private Player(String playerName, int healthPoints, int avatarId,
                   int playerWidth, int playerHeight, double difficulty) {
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.avatarId = avatarId;
        this.x = 0;
        this.y = 0;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
        this.difficulty = difficulty;
        this.weapon = new Weapon(R.drawable.weapon_sword_emerald, 20);
    }
    public static Player getInstance(String playerName, int healthPoints, int avatarId,
                                     int playerWidth, int playerHeight, double difficulty) {
        if (player == null) {
            player = new Player(playerName, healthPoints, avatarId, playerWidth,
                    playerHeight, difficulty);
        }
        return player;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else {
            this.healthPoints = healthPoints;
        }
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public int getWeaponResourceId() {
        return weapon.getWeaponResourceId();
    }

    public double getDifficulty() {
        return difficulty;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getAvatarId() {
        return avatarId;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setGoalX(int x) {
        this.goalX = x;
    }
    public void setGoalY(int y) {
        this.goalY = y;
    }
    public int getGoalX() {
        return goalX;
    }
    public int getGoalY() {
        return goalY;
    }
    public int getPlayerWidth() {
        return playerWidth;
    }
    public int getPlayerHeight() {
        return playerHeight;
    }

    //these are the MOVEMENT METHODS (testing purposes)
    public void moveUp() {
        this.y -= 10;
    }
    public void moveDown() {
        this.y += 10;
    }
    public void moveLeft() {
        this.x -= 10;
    }
    public void moveRight() {
        this.x  += 10;
    }
    // not used in viewModels, only used for testing purposes
    public void move(int keyCode) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_DOWN:
            moveDown();
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            moveUp();
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            moveLeft();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            moveRight();
            break;
        default:
            break;
        }
        Log.d("PlayerPosition", "Player X: " + getX() + ", Y: " + getY());
    }


    // Used for testing purpose
    public boolean isValidMove(List<ImageView> blackTilesList, int x, int y) {
        int playerRight = x + playerWidth;
        int playerBottom = y + playerHeight;
        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTile.getRight();
            int blackTileBottom = blackTile.getBottom();

            // Check 1: boundaries overlap check
            if (playerRight > blackTileLeft
                    && x < blackTileRight
                    && playerBottom > blackTileTop
                    && y < blackTileBottom) {
                // theres a collision, so invalid move
                return false;
            }
            // Check 2: inside boundaries check
            if (x >= blackTileLeft
                    && playerRight <= blackTileRight
                    && y >= blackTileTop
                    && playerBottom <= blackTileBottom) {
                return false;
            }
        }
        int screenWidth = 11 * (90);
        int screenHeight = 13 * (90);
        if (x < 0 || y < 0 || playerRight > screenWidth || playerBottom > screenHeight) {
            return false;
        }

        // theres no collision, so valid move
        return true;
    }

}
