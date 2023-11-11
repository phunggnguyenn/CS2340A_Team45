package com.example.model;
import android.content.Context;

public class EnemyFactory {
    public Enemy createBlueEnemy(Context context, int x, int y) {
        // Create and return a BlueEnemy
        EnemyMovementStrategy blueEnemyMovementStrategy = new BlueEnemyMovementStrategy(x,y);
        return new BlueEnemy(context, x, y, blueEnemyMovementStrategy);
    }

    public Enemy createWhiteEnemy(Context context, int x, int y) {
        EnemyMovementStrategy whiteEnemyMovementStrategy = new WhiteEnemyMovementStrategy();
        // Create and return a WhiteEnemy
        return new WhiteEnemy(context, x, y, whiteEnemyMovementStrategy);
    }

    public Enemy createGreenEnemy(Context context, int x, int y) {
        // Create and return a GreenEnemy
        EnemyMovementStrategy greenEnemyMovementStrategy = new GreenEnemyMovementStrategy(x,y);
        return new GreenEnemy(context, x, y, greenEnemyMovementStrategy);
    }

    public Enemy createYellowEnemy(Context context, int x, int y) {
        // Create and return a YellowEnemy
        EnemyMovementStrategy yellowEnemyMovementStrategy = new YellowEnemyMovementStrategy();
        return new YellowEnemy(context, x, y, yellowEnemyMovementStrategy);
    }
}