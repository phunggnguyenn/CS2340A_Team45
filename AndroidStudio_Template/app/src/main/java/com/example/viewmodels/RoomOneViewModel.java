package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;
public class RoomOneViewModel {
    private int score;
    private Player player;
    private PlayerMovement movementVar;

    public RoomOneViewModel(Player player) {
        this.score = 1000;
        this.player = player;
        this.movementVar = new PlayerMovement();
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
}
