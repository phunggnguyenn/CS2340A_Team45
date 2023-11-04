package com.example.model;

public abstract class Enemies {
    public Enemy generateEnemy() {
        Enemy enemy = createEnemy();
        return enemy;
    }
    public abstract Enemy createEnemy();
}
