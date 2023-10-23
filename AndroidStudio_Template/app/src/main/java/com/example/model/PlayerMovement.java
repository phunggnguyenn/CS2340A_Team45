package com.example.model;

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
        for (ImageView blackTile : blackTilesList) {
            int blackTileLeft = blackTile.getLeft();
            int blackTileTop = blackTile.getTop();
            int blackTileRight = blackTile.getRight();
            int blackTileBottom = blackTile.getBottom();
            // not working as intended
            boolean xOverlap = playerRight > blackTileLeft && x < blackTileRight;
            boolean yOverlap = playerBottom > blackTileTop && y < blackTileBottom;

            if (xOverlap && yOverlap) {
                // There's a collision in both X and Y axes, so it's an invalid move
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
