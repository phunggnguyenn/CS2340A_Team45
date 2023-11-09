package com.example.viewmodels;

import android.graphics.Rect;
import android.util.Log;

import com.example.model.Enemy;
import com.example.model.Player;


public class CollisionObserver implements CollisionObserverStrategy {
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    public CollisionObserver(Player player, Enemy enemy1, Enemy enemy2) {
        this.player = player;
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
    }
    @Override
    public boolean enemyCollision() {
        Log.d("wh co", "wh coordinate x: " + enemy2.getX());
        Log.d("wh co", "wh coordinate y: " + enemy2.getY());
        Log.d("Player co", "player coordinate x: " + player.getX());
        Log.d("Player co", "player coordinate y: " + player.getY());
        Rect playerRect = new Rect(player.getX(), player.getY(), player.getX() + 75, player.getY() + 90);
        Rect enemyRect1 = new Rect(enemy1.getX(), enemy1.getY(), enemy1.getX() + 75, enemy1.getY() + 90);
        Rect enemyRect2 = new Rect(enemy2.getX(), enemy2.getY(), enemy2.getX() + 75, enemy2.getY() + 90);
        if (playerRect.intersect(enemyRect1) || playerRect.intersect(enemyRect2)) {
            return true;
        }
        return false;
    }
}
