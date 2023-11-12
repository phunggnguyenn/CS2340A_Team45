package com.example.model;

import android.widget.ImageView;
//Green enemy's unique movement: direction - diagonal down/up (zigzag), speed - 25
public class GreenEnemyMovementStrategy implements EnemyMovementStrategy {
    private int currentX;
    private int currentY;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up
    private int speed = 25; // Adjust the speed as needed
    private int amplitudeX = 50; // Adjust the amplitude of zigzag in the X direction
    private int amplitudeY = 50; // Adjust the amplitude of zigzag in the Y direction

    public GreenEnemyMovementStrategy(int startX, int startY) {
        this.currentX = startX;
        this.currentY = startY;
    }

    @Override
    public void move(Enemy enemy) {
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

        // Set the new position
        enemy.setX(currentX);
        enemy.setY(currentY);
        // Update the enemy's position visually
        view.setX(currentX);
        view.setY(currentY);
    }
}
