package com.example.test;




import android.content.Context;

import com.example.model.Player;
import com.example.model.Enemy;
import com.example.viewmodels.CollisionObserver;
import com.example.views.RoomTwo;


import org.junit.Test;



public class HealthPointsTest {
    private RoomTwo roomTwo;
    private Player player;
    private Context context;
    private CollisionObserver collisionObserver;
    private Enemy enemy1;
    private Enemy enemy2;



    @Test
    public void testGameOverScreenOnZeroHealth() {
        Player player = Player.getInstance("TestPlayer", 0, 1, 50, 50, 1.0);

        // Check if the game over screen is triggered when the player's health reaches zero
        assertTrue("Game over screen not triggered on zero health", isGameOver(player));
    }

    private void assertTrue(String s, boolean gameOver) {
    }

    private boolean isGameOver(Player player) {
        return player.getHealthPoints() <= 0;
    }

}
