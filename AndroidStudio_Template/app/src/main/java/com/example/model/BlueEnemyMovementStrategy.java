package com.example.model;

import android.widget.ImageView;
//THIS ENEMY'S UNIQUE MOVEMENT PATTERN - DIRECTION (diagonal) AND SPEED (fast)
public class BlueEnemyMovementStrategy implements EnemyMovementStrategy{
    private static final int MOVEMENT_RANGE_X = 200; // Customize the range as needed
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up
    private int currentX = 0;
    private int currentY = 0;


    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();
        // Update the enemy's position based on the current direction
        currentX += directionX * 30; // Speed (we can adjust if needed)
        currentY += directionY * 30; // Speed (we can adjust if needed)
        int screenWidth = 11 * (90);
        int screenHeight = 13 * (90);
        // Calculate the right and bottom boundaries
        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();

        //change direction given boundary check!
        if (currentX >= rightBoundary || currentX <= 0) {
            directionX *= -1; // Reverse direction
            currentX += directionX * 30; // Move it back inside the boundary
        }
        if (currentY >= bottomBoundary || currentY <= 0) {
            directionY *= -1; // Reverse direction
            currentY += directionY * 30; // Move it back inside the boundary
        }

        // update the enemy's pos on view
        view.setX(currentX);
        view.setY(currentY);
    }
}


