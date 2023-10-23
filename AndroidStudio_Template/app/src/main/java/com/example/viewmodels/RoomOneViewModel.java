package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;
import com.example.model.PlayerMovementStrategy;
import com.example.views.RoomTwo;
import com.example.views.RoomOne;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class RoomOneViewModel {
    private int score;
    private Player player;
    private PlayerMovementStrategy movementStrategy;
    private Context context;
    private PlayerObserverStrategy observer;
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
    // Strategy pattern for handling player input for movement
    public void handleKeyEvent(int keyCode, List<ImageView> blackTilesList, ImageView avatar) {
        movementStrategy = new PlayerMovement();
        int oldX = player.getX();
        int oldY = player.getY();
        movementStrategy.move(player, keyCode);
        int newX = player.getX();
        int newY = player.getY();
        Log.d("RoomOne", "Player position: x=" + newX + ", y=" + newY);
        Log.d("RoomOne", "Goal position: x=" + player.getGoalX() + ", y=" + player.getGoalY());
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
    public boolean checkReachedGoal() {
        observer = new PlayerObserver(player);
        return observer.playerReachedGoal();
    }
    public void moveToNextRoom() {
        Intent room2Intent = new Intent(context, RoomTwo.class);
        room2Intent.putExtra("player", player);
        room2Intent.putExtra("score", score);
        context.startActivity(room2Intent);
        ((Activity) context).finish();
    }
}
