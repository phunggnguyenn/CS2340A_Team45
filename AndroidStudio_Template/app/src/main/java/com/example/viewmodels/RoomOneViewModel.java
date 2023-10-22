package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;
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
    private PlayerMovement movementVar;
    private Context context;
    public RoomOneViewModel(Player player, Context context) {
        this.score = 1000;
        this.player = player;
        this.movementVar = new PlayerMovement();
        this.context = context;
    }
    // Player Movement
    public void movePlayerUp() {
        player.moveUp();
    }
    public void movePlayerDown() {
        player.moveDown();
    }
    public void movePlayerLeft() {
        player.moveLeft();
    }
    public void movePlayerRight() {
        player.moveRight();
    }
    public boolean isValidMove(List<ImageView> blackTilesList, int x, int y) {
        return player.isValidMove(blackTilesList, x, y);
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
}
