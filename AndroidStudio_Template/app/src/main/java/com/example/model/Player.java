package com.example.model;

import java.io.Serializable;

import android.view.KeyEvent;
import android.widget.ImageView;

import com.example.viewmodels.PlayerObserver;

import java.util.ArrayList;
import java.util.List;


public class Player implements Serializable {
    private int x; //for movement
    private int y; //for movement
    private int playerWidth;
    private int playerHeight;
    private String playerName;
    private int healthPoints;
    private int avatarId;
    private static Player player;
    private List<PlayerObserver> observers;
    private int goalX;
    private int goalY;


    private Player(String playerName, int healthPoints, int avatarId,
                   int playerWidth, int playerHeight) {
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.avatarId = avatarId;
        this.x = 0;
        this.y = 0;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
        this.observers = new ArrayList<>();
    }
    public static Player getInstance(String playerName, int healthPoints, int avatarId,
                                     int playerWidth, int playerHeight) {
        if (player == null) {
            player = new Player(playerName, healthPoints, avatarId, playerWidth, playerHeight);
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

    //these are the MOVEMENT METHODS
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
    // Observer
    public void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (PlayerObserver observer : observers) {
            observer.playerReachedGoal();
        }
    }
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
    }

    //this method is supposed to check for collisions with black tiles
    // and check if it goes beyond screenheight
    //but its not working that way rn
    //Made blacktilesList containing references of all blackTiles (walls)
    // Feel free to delete it
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