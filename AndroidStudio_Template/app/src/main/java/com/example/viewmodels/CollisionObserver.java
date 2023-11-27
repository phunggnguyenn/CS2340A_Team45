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
    private PowerUp powerUp1;
    private PowerUp powerUp2;
    private PowerUp powerUp3;
    public CollisionObserver(Player player, Enemy enemy1, Enemy enemy2,
                             PowerUp powerUp1, PowerUp powerUp2, PowerUp powerUp3) {
        this.player = player;
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.powerUp1 = powerUp1;
        this.powerUp2 = powerUp2;
        this.powerUp3 = powerUp3;
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
    public int powerUpCollision() {
        if (powerUp1 == null || powerUp2 == null || powerUp3 == null) {
            return -1;
        }
        Rect playerRect = new Rect(player.getX(), player.getY(), player.getX() + 75,
                player.getY() + 90);
        Rect powerUp1Rect = new Rect(powerUp1.getX(), powerUp1.getY(), powerUp1.getX() + 75,
                powerUp1.getY() + 90);
        if (playerRect.intersect(powerUp1Rect) && !powerUp1.isCollected()) {
            powerUp1.setCollected(true);
            return 1;
        }
        Rect powerUp2Rect = new Rect(powerUp2.getX(), powerUp2.getY(), powerUp2.getX() + 75,
                powerUp2.getY() + 90);
        if (playerRect.intersect(powerUp2Rect) && !powerUp2.isCollected()) {
            powerUp2.setCollected(true);
            return 2;
        }
        Rect powerUp3Rect = new Rect(powerUp3.getX(), powerUp3.getY(), powerUp3.getX() + 75,
                powerUp3.getY() + 90);
        if (playerRect.intersect(powerUp3Rect) && !powerUp3.isCollected()) {
            powerUp3.setCollected(true);
            return 3;
        }
        return -1;
    }
    public void enemyAttacked() {
        // Handle enemy destruction when attacked
        if (enemyCollision()) {
            Log.d("CollisionObserver", "Enemy attacked");
            collidedEnemy.getView().setImageResource(R.drawable.skull);
        }
    }


}
