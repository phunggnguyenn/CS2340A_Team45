package com.example.model;

import android.widget.ImageView;
import java.util.Random;

// Blue enemy's unique movement: direction - straight down/up, speed - 70
public class BlueEnemyMovementStrategy implements EnemyMovementStrategy {
    private static final int SPEED = 70; // Customize the speed as needed
    private int currentX;
    private int currentY;
    private int directionY = 1; // 1 for down

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

        // Update the enemy's position based on the current direction and speed
        int newY = currentY + directionY * SPEED;

        int screenHeight = 13 * 90; // Adjust as needed

        // Calculate the bottom boundary for enemy movement
        int bottomBoundary = screenHeight - view.getHeight();

        // Reverse direction if reaching the bottom boundary
        if (newY >= bottomBoundary || newY <= 0) {
            directionY *= -1; // Reverse Y-direction
        }

        // Apply the straight line pattern by adjusting position
        currentY = Math.max(0, Math.min(newY, bottomBoundary));

        // Update the enemy's position visually
        view.setX(currentX);
        view.setY(currentY);
    }
}
