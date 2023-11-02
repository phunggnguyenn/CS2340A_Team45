package com.example.model;

import android.widget.ImageView;
//PENDING to change - unique movement
public class YellowEnemyMovementStrategy  implements EnemyMovementStrategy{
    private static final int SPEED = 5; // Customize the speed as needed
    private int currentX = 0;
    private int currentY = 0;
    private int directionX = 1; // 1 for right, -1 for left
    private int directionY = 1; // 1 for down, -1 for up

    @Override
    public void move(Enemy enemy) {
        ImageView view = enemy.getView();

        // Calculate the new position based on the current direction and speed
        int newX = currentX + directionX * SPEED;
        int newY = currentY + directionY * SPEED;

        int screenWidth = 11 * (90); // Adjust as needed
        int screenHeight = 13 * (90); // Adjust as needed

        // Calculate the right and bottom boundaries for enemy movement
        int rightBoundary = screenWidth - view.getWidth();
        int bottomBoundary = screenHeight - view.getHeight();

        // Reverse direction if reaching movement boundaries
        if (newX >= rightBoundary || newX <= 0) {
            directionX *= -1; // Reverse X-direction
        }
        if (newY >= bottomBoundary|| newY <= 0) {
            directionY *= -1; // Reverse Y-direction
        }

        // Update the enemy's position visually
        view.setX(newX);
        view.setY(newY);

        // Update the current position
        currentX = newX;
        currentY = newY;
    }
}
