package com.example.model;

import java.io.Serializable;
import android.widget.ImageView;
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


    private Player(String playerName, int healthPoints, int avatarId, int playerWidth, int playerHeight) {
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.avatarId = avatarId;
        this.x = 0;
        this.y = 0;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
    }
    public static Player getInstance(String playerName, int healthPoints, int avatarId, int playerWidth, int playerHeight) {
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

    //these are the MOVEMENT METHODS
    public void moveUp() {
        this.y -=10;
    }
    public void moveDown() {
        this.y +=10;
    }
    public void moveLeft() {
        this.x -= 10;
    }
    public void moveRight() {
        this.x  += 10;
    }

    //this method is supposed to check for collisions with black tiles and if it goes beyond screenheight
    //but its not working that way rn
    //Made blacktilesList containing references of all blackTiles(walls it shouldnt be able to move thru)
    public boolean isValidMove(List<ImageView> blackTilesList, int x, int y) {
        int playerRight = x + playerWidth;
        int playerBottom = y + playerHeight;

        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTile.getRight();
            int blackTileBottom = blackTile.getBottom();

            if (playerRight >= blackTileLeft &&
                    x <= blackTileRight &&
                    playerBottom >= blackTileTop &&
                    y <= blackTileBottom) {
                // Collision detected, so it's INVALID
                return false;
            }
        }
        // If no collision detected with any black tiles, it's valid
        return true;
    }

}