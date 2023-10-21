package com.example.viewmodels;

import com.example.model.Player;
import com.example.model.PlayerMovement;

public class RoomThreeViewModel {
    private int score;
    private Player player;
    private PlayerMovement movementVar;

    public RoomThreeViewModel(Player player, int score) {
        this.score = score;
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
