package com.example.model;

import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import java.util.List;

public class PlayerMovement implements PlayerMovementStrategy {
    private List<ImageView> blackTilesList;

    public PlayerMovement(List<ImageView> blackTilesList) {
        this.blackTilesList = blackTilesList;
    }

    @Override
    public void move(Player player, int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                player.setY(player.getY() + 10);
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                player.setY(player.getY() - 10);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                player.setX(player.getX() - 10);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                player.setX(player.getX() + 10);
                break;
            default:
                return;
        }

    }

    @Override
    public boolean isValidMove(List<ImageView> blackTilesList, int x, int y, Player player) {
<<<<<<< HEAD
        int playerRight = x + player.getPlayerWidth();
        int playerBottom = y + player.getPlayerHeight();
        int blackTileWidth = 80;
=======
        Log.d("BlackTilesList", "BlackTilesList size: " + blackTilesList.size());
        int playerRight = x + 60;
        int playerBottom = y + 60;
        Rect playerRect = new Rect(x, y, playerRight, playerBottom);
>>>>>>> main
        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTileLeft + blackTileWidth;
            int blackTileBottom = blackTileTop + blackTileWidth;
            // not working as intended
            boolean xOverlap = playerRight > blackTileLeft && x < blackTileRight;
            boolean yOverlap = playerBottom > blackTileTop && y < blackTileBottom;
            Rect playerRect = new Rect(x, y, x + 60, y + 60);
            for (ImageView blackTile2 : blackTilesList) {
                blackTileLeft = blackTile.getLeft();
                blackTileTop = blackTile.getTop();
                blackTileRight = blackTile.getRight();
                blackTileBottom = blackTile.getBottom();
                Rect blackTileRect = new Rect(blackTileLeft, blackTileTop, blackTileRight, blackTileBottom);

<<<<<<< HEAD
                if (playerRect.intersect(blackTileRect)) {
                    Log.d("Collision", "Intersection detected at X: " + x + ", Y: " + y);
                    Log.d("Collision", "Player Rect: " + playerRect.toString());
                    Log.d("Collision", "Black Tile Rect: " + blackTileRect.toString());
                    return false;
                }
=======
                if (playerRect.intersect(blackTileRect)) {
                    return false;
>>>>>>> main
                }
            }

            int screenWidth = 11 * (90);
            int screenHeight = 13 * (90);
            if (x < 0 || y < 0 || x > screenWidth || y > screenHeight) {
                return false;
            }
            // There's no collision, so valid move
            return true;
        }
    }
