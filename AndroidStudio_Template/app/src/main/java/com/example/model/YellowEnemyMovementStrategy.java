package com.example.model;

import android.widget.ImageView;
//Unique movements are position/direction and speed.
// 2) Direction: yellow is going back n forth on top of screen
// 1) Speed # also varies from other enemies - speed x dir-> 40
public class YellowEnemyMovementStrategy  implements EnemyMovementStrategy{
    private static final int SPEED_X = 40; // Customize the X-axis speed as needed
    private static final int SPEED_Y = 0; // Keep the Y-axis speed as 0
    private int currentX = 0;
    private int currentY = 0;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 0; // 0 for no vertical movement

    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();

        // Calculate the new position based on the current direction and speed
        int newX = currentX + directionX * SPEED_X;
        int newY = currentY + directionY * SPEED_Y;

        int screenWidth = 11 * (90); // Adjust as needed
        int screenHeight = 13 * (90); // Adjust as needed

        // Customize the right and bottom boundaries for enemy movement
        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();

        // Set the boundaries for moving from top-left to top-right
        int minX = 0; // Minimum X-coordinate
        int maxX = rightBoundary; // Maximum X-coordinate
        int minY = 0; // Minimum Y-coordinate
        int maxY = 0; // Maximum Y-coordinate

        // Change direction if reaching movement boundaries
        if (newX >= maxX || newX <= minX) {
            directionX *= -1; // Reverse X-direction
        }

        // Set the new position
        enemy.setX(newX);
        enemy.setY(newY);
        // Update the enemy's position visually
        view.setX(newX);
        view.setY(newY);

        // Update the current position
        currentX = newX;
        currentY = newY;
    }
}