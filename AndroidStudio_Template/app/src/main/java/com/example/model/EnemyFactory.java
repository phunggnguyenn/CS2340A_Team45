package com.example.model;
import android.content.Context;

public class EnemyFactory {
    public Enemy createBlueEnemy(Context context, int x, int y) {
        // Create and return a BlueEnemy
        return new BlueEnemy(context, x, y);
    }

    public Enemy createWhiteEnemy(Context context, int x, int y) {
        // Create and return a WhiteEnemy
        return new WhiteEnemy(context, x, y);
    }

    public Enemy createGreenEnemy(Context context, int x, int y) {
        // Create and return a GreenEnemy
        return new GreenEnemy(context, x, y);
    }

    public Enemy createYellowEnemy(Context context, int x, int y) {
        // Create and return a YellowEnemy
        return new YellowEnemy(context, x, y);
    }
}
