package com.example.model;
import android.view.KeyEvent;

public class PlayerMovement {
    public void move(Player player, int keyCode) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_DOWN:
            player.moveDown();
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            player.moveUp();
            break;
        case KeyEvent.KEYCODE_DPAD_LEFT:
            player.moveLeft();
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }

}
