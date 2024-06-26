package com.example.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import com.example.model.Player;
import com.example.model.PlayerMovement;
import com.example.model.PlayerMovementStrategy;
import com.example.views.GameEndActivity;
import java.util.List;

public class RoomThreeViewModel {
    private int score;
    private Player player;
    private PlayerMovementStrategy movementStrategy;
    private Context context;
    private PlayerObserverStrategy observer;
    private CollisionObserver collisionObserver;

    public RoomThreeViewModel(Player player, int score, Context context) {
        this.score = score;
        this.player = player;
        this.context = context;
        player.setGoalX(895);
        player.setGoalY(5);
    }
    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score += points;
        if (score < 0) {
            score = 0; // Ensure the score doesn't go below 0
        }
    }
    public boolean handleKeyEvent(int keyCode, List<ImageView> blackTilesList, ImageView avatar) {
        movementStrategy = new PlayerMovement(blackTilesList, collisionObserver);
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
            return true;
        } else {
            player.setX(oldX);
            player.setY(oldY);
            avatar.setX(oldX);
            avatar.setY(oldY);
            return false;
        }
    }
    public boolean checkReachedGoal() {
        observer = new PlayerObserver(player);
        return observer.playerReachedGoal();
    }
    public void moveToNextRoom() {
        Intent endIntent = new Intent(context, GameEndActivity.class);
        endIntent.putExtra("player", player);
        endIntent.putExtra("score", score);
        context.startActivity(endIntent);
        ((Activity) context).finish();
    }

    public void enemyDestroyed() {
        updateScore(50);
    }
}