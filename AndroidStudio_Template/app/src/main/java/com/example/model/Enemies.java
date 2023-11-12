package com.example.model;

public abstract class Enemies {
    public Enemy generateEnemy() {
        Enemy enemy = createEnemy();
        enemy.generate();
        return enemy;
    }
    public abstract Enemy createEnemy();
}
