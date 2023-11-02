package com.example.model;
import android.widget.ImageView;
import java.util.Random;
//PENDING to change - unique movement
public class GreenEnemyMovementStrategy  implements EnemyMovementStrategy{
    private static final int MOVEMENT_RANGE_X = 200; // Customize X-axis range
    private static final int MOVEMENT_RANGE_Y = 200; // Customize Y-axis range
    private int currentX = 0;
    private int currentY = 0;
    private Random random = new Random();

    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();

        // Generate random values for X and Y within the specified ranges
        int newX = currentX + random.nextInt(MOVEMENT_RANGE_X) - MOVEMENT_RANGE_X / 2;
        int newY = currentY + random.nextInt(MOVEMENT_RANGE_Y) - MOVEMENT_RANGE_Y / 2;

        // Ensure the enemy stays within the boundaries of the screen
        // PENDING BOUNDARY CHECK!!!

        // Update the enemy's position visually
        view.setX(newX);
        view.setY(newY);

        // Update the current position
        currentX = newX;
        currentY = newY;
    }
}
