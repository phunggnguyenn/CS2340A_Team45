package com.example.model;

import android.widget.ImageView;

//Unique movements is ANGLE
// 1) goes in big circles in an angle
// pending:needs to stay within upper screen boundary
public class WhiteEnemyMovementStrategy implements EnemyMovementStrategy {
    private static final int RADIUS = 500; // Customize the radius as needed
    private double angle = 100;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up

    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();

        // Calculate the new position based on the circular path
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;

        int newX = (int) (centerX + RADIUS * Math.cos(angle));
        int newY = (int) (centerY + RADIUS * Math.sin(angle));

        int screenWidth = 11 * 90; // Adjust as needed
        int screenHeight = 13 * 90; // Adjust as needed

        // Calculate the boundaries for enemy movement
        // Calculate the right, bottom, and top boundaries for enemy movement
        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();
        int topBoundary = 0; // The top boundary is set to the top of the screen
        int leftBoundary = 0; // The left boundary is set to the left of the screen

        // Ensure the enemy stays within all boundaries
        newX = Math.max(leftBoundary, Math.min(newX, rightBoundary));
        newY = Math.max(topBoundary, Math.min(newY, bottomBoundary));

        // Update the enemy's position visually
        view.setX(newX);
        view.setY(newY);

        // Adjust the angle for the next movement step
        angle += 20; // Adjust the rotation speed as needed
    }
}