package com.example.model;

import android.widget.ImageView;
import java.util.Random;

// Green enemy's unique movement: direction - diagonal down/up (zigzag), speed - 25
public class GreenEnemyMovementStrategy implements EnemyMovementStrategy {
    private int currentX;
    private int currentY;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up
    private int speed = 50; // Adjust the speed as needed
    private int amplitudeX = 50; // Adjust the amplitude of zigzag in the X direction
    private int amplitudeY = 50; // Adjust the amplitude of zigzag in the Y direction

    @Override
    public void move(Enemy enemy) {
        if (currentX == 0 && currentY == 0) {
            // Generate random initial position within the screen boundaries
            Random random = new Random();
            int screenWidth = 11 * 90; // Adjust as needed
            int screenHeight = 13 * 90; // Adjust as needed

            currentX = random.nextInt(screenWidth - enemy.getView().getWidth());
            currentY = random.nextInt(screenHeight - enemy.getView().getHeight());
        }

        ImageView view = enemy.getView();

        // Update the enemy's position based on the current direction
        currentX += directionX * speed;
        currentY += directionY * speed;

        // Calculate the boundaries for enemy movement
        int screenWidth = 11 * 90; // Adjust as needed
        int screenHeight = 13 * 90; // Adjust as needed

        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();

        // Implement zigzag pattern
        if (currentX >= rightBoundary || currentX <= 0) {
            directionX *= -1; // Reverse direction in X-axis
        }
        if (currentY >= bottomBoundary || currentY <= 0) {
            directionY *= -1; // Reverse direction in Y-axis
        }

        // Apply zigzag pattern by adjusting position
        currentX = Math.max(0, Math.min(currentX, rightBoundary));
        currentY = Math.max(0, Math.min(currentY, bottomBoundary));

        // Update the enemy's position visually
        view.setX(currentX);
        view.setY(currentY);
    }
}
