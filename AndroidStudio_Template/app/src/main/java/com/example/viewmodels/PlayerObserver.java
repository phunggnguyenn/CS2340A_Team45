package com.example.viewmodels;

import com.example.model.Player;

public class PlayerObserver implements PlayerObserverStrategy {
    private Player player;
    public PlayerObserver(Player player) {
        this.player = player;
    }
    @Override
    public boolean playerReachedGoal() {
        if (player.getX() >= player.getGoalX() && player.getX() <= player.getGoalX() + 20
                && player.getY() == player.getGoalY()) {
            return true;
        }
        return false;
    }
}
