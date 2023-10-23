package com.example.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import com.example.model.Player;
import com.example.model.PlayerMovement;
import com.example.model.PlayerMovementStrategy;
import com.example.views.RoomThree;
import com.example.views.RoomTwo;

import java.util.List;

public class RoomTwoViewModel {
    private int score;
    private Player player;
    private PlayerMovementStrategy movementStrategy;
    private Context context;
    private PlayerObserverStrategy observer;

    public RoomTwoViewModel(Player player, int score, Context context) {
        this.score = score;
        this.player = player;
        this.movementStrategy = new PlayerMovement();
        this.context = context;
    }
    public int getScore() {
        return score;
    }

    public void updateScore(int change) {
        score += change;
        if (score < 0) {
            score = 0; // Ensure the score doesn't go below 0
        }
    }
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
        Intent room3Intent = new Intent(context, RoomThree.class);
        room3Intent.putExtra("player", player);
        room3Intent.putExtra("score", score);
        context.startActivity(room3Intent);
        ((Activity) context).finish();
    }
}
