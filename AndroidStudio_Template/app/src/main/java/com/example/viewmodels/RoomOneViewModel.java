package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;
import com.example.model.PlayerMovementStrategy;
import com.example.views.RoomTwo;
import com.example.views.RoomOne;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class RoomOneViewModel implements PlayerObserver {
    private int score;
    private Player player;
    private PlayerMovementStrategy movementStrategy;
    private Context context;
    public RoomOneViewModel(Player player, Context context) {
        this.score = 1000;
        this.player = player;
        this.context = context;
    }
    // Score Feature
    public int getScore() {
        return score;
    }
    public void updateScore(int change) {
        score += change;
        if (score < 0) {
            score = 0; // Ensure the score doesn't go below 0
        }
    }
    @Override
    public void playerReachedGoal() {
        movePlayerToNextRoom();
    }
    private void movePlayerToNextRoom() {
        Intent room2Intent = new Intent(context, RoomTwo.class);
        room2Intent.putExtra("player", player);
        room2Intent.putExtra("score", score);
        context.startActivity(room2Intent);
        ((Activity) context).finish();
    }
    // Strategy pattern for handling player input for movement
    public void handleKeyEvent(int keyCode, List<ImageView> blackTilesList, ImageView avatar) {
        movementStrategy = new PlayerMovement();
        int oldX = player.getX();
        int oldY = player.getY();
        movementStrategy.move(player, keyCode);
        int newX = player.getX();
        int newY = player.getY();
        if (movementStrategy.isValidMove(blackTilesList, newX, newY, player)) {
            player.setX(newX);
            player.setY(newY);
            avatar.setX(newX);
            avatar.setY(newY);
        } else {
            player.setX(oldX);
            player.setY(oldY);
            avatar.setX(oldX);
            avatar.setY(oldY);
        }
    }
}
