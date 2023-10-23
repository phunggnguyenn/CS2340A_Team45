package com.example.viewmodels;
import android.content.Context;
import android.content.Intent;

import com.example.model.Player;
import com.example.views.RoomTwo;

import java.util.ArrayList;
import java.util.List;
public class PlayerObserver implements PlayerObserverStrategy {
    private Player player;
    public PlayerObserver(Player player) {
        this.player = player;
    }
    @Override
    public boolean playerReachedGoal() {
        if (player.getX() == player.getGoalX()&& player.getY() == player.getGoalY()) {
            return true;
        }
        return false;
    }
}
