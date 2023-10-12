package com.example.viewmodels;

import com.example.model.Player;

public class RoomThreeViewModel {
    private int score;
    private Player player;

    public RoomThreeViewModel(Player player, int score) {
        this.score = score;
        this.player = player;
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
