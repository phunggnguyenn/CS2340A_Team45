package com.example.viewmodels;

import android.graphics.Rect;
import android.util.Log;

import com.example.demo_2340.R;
import com.example.model.Enemy;
import com.example.model.Player;
import com.example.model.PowerUp;

public class CollisionObserver implements CollisionObserverStrategy {
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy collidedEnemy;
    private PowerUp powerUp;
    public CollisionObserver(Player player, Enemy enemy1, Enemy enemy2, PowerUp powerUp) {
        this.player = player;
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.powerUp = powerUp;
    }
    @Override
    public boolean enemyCollision() {
        if (enemy1 == null || enemy2 == null) {
            // Handle the case where one of the Enemy objects is null
            return false;
        }
        Log.d("wh co", "wh coordinate x: " + enemy2.getX());
        Log.d("wh co", "wh coordinate y: " + enemy2.getY());
        Log.d("Player co", "player coordinate x: " + player.getX());
        Log.d("Player co", "player coordinate y: " + player.getY());
        Rect playerRect = new Rect(player.getX(), player.getY(), player.getX() + 75,
                player.getY() + 90);
        Rect enemyRect1 = new Rect(enemy1.getX(), enemy1.getY(), enemy1.getX() + 75,
                enemy1.getY() + 90);
        Rect enemyRect2 = new Rect(enemy2.getX(), enemy2.getY(), enemy2.getX() + 75,
                enemy2.getY() + 90);
        if (playerRect.intersect(enemyRect1)) {
            collidedEnemy = enemy1;
            return true;
        }
        if (playerRect.intersect(enemyRect2)) {
            collidedEnemy = enemy2;
            return true;
        }
        return false;
    }
    @Override
    public boolean powerUpCollision() {
        if (powerUp == null || powerUp.isCollected()) {
            return false;
        }
        Rect playerRect = new Rect(player.getX(), player.getY(), player.getX() + 75,
                player.getY() + 90);
        Rect powerUpRect = new Rect(powerUp.getX(), powerUp.getY(), powerUp.getX() + 75,
                powerUp.getY() + 90);
        if (playerRect.intersect(powerUpRect)) {
            powerUp.setCollected(true);
            return true;
        }
        return false;
    }
    public void enemyAttacked() {
        if (collidedEnemy != null) {
            // Handle enemy destruction when attacked
            collidedEnemy.getView().setImageResource(R.drawable.skull);
        }
    }


}
