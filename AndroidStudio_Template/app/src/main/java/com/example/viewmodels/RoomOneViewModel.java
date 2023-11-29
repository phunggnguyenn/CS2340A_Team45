package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;
import com.example.model.PlayerMovementStrategy;
import com.example.views.RoomTwo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import java.util.List;




public class RoomOneViewModel {
    private int score;
    private Player player;
    private PlayerMovementStrategy movementStrategy;
    private Context context;
    private PlayerObserverStrategy playerObserver;
    private CollisionObserver collisionObserver;

    public RoomOneViewModel(Player player, Context context) {
        this.score = 1000;
        this.player = player;
        this.context = context;
        player.setGoalX(715);
        player.setGoalY(5);

    }
    // Score Feature
    public int getScore() {
        return score;
    }
    public void updateScore(int points) {
        score += points;
        if (score < 0) {
            score = 0; // Ensure the score doesn't go below 0
        }
    }
    // Strategy pattern for handling player input for movement
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
        playerObserver = new PlayerObserver(player);
        return playerObserver.playerReachedGoal();
    }

    public void moveToNextRoom() {
        Intent room2Intent = new Intent(context, RoomTwo.class);
        room2Intent.putExtra("player", player);
        room2Intent.putExtra("score", score);
        context.startActivity(room2Intent);
        ((Activity) context).finish();
    }

    public void enemyDestroyed() {
        updateScore(50);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void setCollisionObserver(CollisionObserver collisionObserver) {
        this.collisionObserver = collisionObserver;
    }
}