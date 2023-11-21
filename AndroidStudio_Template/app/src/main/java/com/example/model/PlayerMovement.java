package com.example.model;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.graphics.Rect;

import com.example.viewmodels.CollisionObserver;

import java.util.List;

public class PlayerMovement implements PlayerMovementStrategy {
    private List<ImageView> blackTilesList;
    private CollisionObserver collisionObserver;
    private ImageView avatarImageView;
    private ImageView weaponImageView;

    public PlayerMovement(List<ImageView> blackTilesList, CollisionObserver collisionObserver) {
        this.blackTilesList = blackTilesList;
        this.collisionObserver = collisionObserver;
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
            case KeyEvent.KEYCODE_SPACE:
                // Handle attack when the space key is pressed
                if (collisionObserver != null && collisionObserver.enemyCollision()) {
                    initiateAttack();
                }
                break;
            default:
                break;
        }
    }

    private void initiateAttack() {
        // Call the handleAttack method in CollisionObserver to perform the attack logic
        collisionObserver.enemyAttacked();
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
        // there's no collision, so valid move
        return true;
    }

    public void setImageViews(ImageView avatarImageView, ImageView weaponImageView) {
        this.avatarImageView = avatarImageView;
        this.weaponImageView = weaponImageView;
    }
}
