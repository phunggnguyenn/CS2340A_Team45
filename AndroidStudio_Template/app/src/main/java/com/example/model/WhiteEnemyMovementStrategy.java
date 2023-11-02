package com.example.model;
import android.widget.ImageView;

//WHITE ENEMY UNIQUE MOVEMENT PATTERN IS: angle of movement (Goes in big circles:))

//ENSURE IT STAYS IN UPPER BOUND
public class WhiteEnemyMovementStrategy  implements EnemyMovementStrategy{
    private static final int RADIUS = 800; // Customize the radius as needed
    private double angle = 0;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up

    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();

        // calculate the new position based on the circular path
        int centerX = view.getWidth() / 2;
        int centerY = view.getHeight() / 2;

        int newX = (int) (centerX + RADIUS * Math.cos(angle));
        int newY = (int) (centerY + RADIUS * Math.sin(angle));

        int screenWidth = 11 * (90); // Adjust as needed
        int screenHeight = 13 * (90); // Adjust as needed

        // calculate the right and bottom boundaries for enemy movement
        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();

        if (newX >= rightBoundary || newX <= 0) {
            directionX *= -1; // go in reverse direction
        }
        if (newY >= bottomBoundary || newY <= 0) {
            directionY *= -1; // go in reverse direction
        }

        // boundary check!
        newX = Math.max(0, Math.min(newX, rightBoundary));
        newY = Math.max(0, Math.min(newY, bottomBoundary));

        // update the enemy's pos on view
        view.setX(newX);
        view.setY(newY);

        // Adjust the angle for the next movement step
        angle += 0.9; // Adjust the rotation speed as needed
    }
}
