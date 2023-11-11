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
        Log.d("BlackTilesList", "BlackTilesList size: " + blackTilesList.size());
        int playerRight = x + 75;
        int playerBottom = y + 90;
        Rect playerRect = new Rect(x, y, playerRight, playerBottom);
        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTile.getRight();
            int blackTileBottom = blackTile.getBottom();
            Rect blackTileRect = new Rect(blackTileLeft, blackTileTop, blackTileRight,
                    blackTileBottom);

            if (playerRect.intersect(blackTileRect)) {
                return false;
            }

        }
        int screenWidth = 11 * (90);
        int screenHeight = 13 * (90);
        if (x < 0 || y < 0 || x > screenWidth || y > screenHeight) {
            return false;
        }
        // theres no collision, so valid move
        return true;
    }

}