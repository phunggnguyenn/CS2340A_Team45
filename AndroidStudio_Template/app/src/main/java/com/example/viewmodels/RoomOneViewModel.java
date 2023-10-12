package com.example.viewmodels;

import com.example.model.Player;
public class RoomOneViewModel {
    private int score;
    private Player player;

    public RoomOneViewModel(Player player) {
        this.score = 1000;
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
