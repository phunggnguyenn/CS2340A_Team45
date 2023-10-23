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
            break;
        }
    }
    @Override
    public boolean isValidMove(List<ImageView> blackTilesList, int x, int y, Player player) {
        int playerRight = x + player.getPlayerWidth();
        int playerBottom = y + player.getPlayerHeight();
        Rect playerRect = new Rect(x, y, x + 60, y + 60);
        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTile.getRight();
            int blackTileBottom = blackTile.getBottom();
            Rect blackTileRect = new Rect(blackTileLeft, blackTileTop, blackTileRight, blackTileBottom);

            if (playerRect.intersect(blackTileRect)) {
                Log.d("Collision", "Intersection detected at X: " + x + ", Y: " + y);
                Log.d("Collision", "Player Rect: " + playerRect.toString());
                Log.d("Collision", "Black Tile Rect: " + blackTileRect.toString());
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
