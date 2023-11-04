package com.example.model;
import android.content.Context;

public class EnemyFactory {
    public Enemy createBlueEnemy(Context context) {
        EnemyMovementStrategy blueEnemyMovementStrategy = new BlueEnemyMovementStrategy();
        return new BlueEnemy(context, blueEnemyMovementStrategy);
    }

    public Enemy createWhiteEnemy(Context context) {
        EnemyMovementStrategy whiteEnemyMovementStrategy = new WhiteEnemyMovementStrategy();
        return new WhiteEnemy(context, whiteEnemyMovementStrategy);
    }

    public Enemy createGreenEnemy(Context context) {
        EnemyMovementStrategy greenEnemyMovementStrategy = new GreenEnemyMovementStrategy();
        return new GreenEnemy(context, greenEnemyMovementStrategy);
    }

    public Enemy createYellowEnemy(Context context) {
        EnemyMovementStrategy yellowEnemyMovementStrategy = new YellowEnemyMovementStrategy();
        return new YellowEnemy(context, yellowEnemyMovementStrategy);
    }
}
