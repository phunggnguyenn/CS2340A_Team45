package com.example.test;

import com.example.model.BlueEnemy;
import com.example.model.EnemyMovementStrategy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnemyTest {
    /**
     * Member: Mahathi Gumudavelli
     * This method tests blue enemy player movement
     */
    @Test
    public void testMove() {  //testing blue enemy movement
        int initialX = 50;
        int initialY = 50;
        EnemyMovementStrategy movementStrategy = enemy -> {
            enemy.setX(enemy.getX() + 5);
            enemy.setY(enemy.getY() + 5);
        };
        BlueEnemy blueEnemy = new BlueEnemy(null, initialX, initialY, movementStrategy);
        blueEnemy.move();
        assertEquals(initialX + 5, blueEnemy.getX());
        assertEquals(initialY + 5, blueEnemy.getY());
    }
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if it gets x
     */
    @Test
    public void testGetX() {
        int initialX = 30;
        int initialY = 40;
        EnemyMovementStrategy movementStrategy = enemy -> { };
        BlueEnemy blueEnemy = new BlueEnemy(null, initialX, initialY, movementStrategy);
        int x = blueEnemy.getX();
        assertEquals(initialX, x);
    }
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if it gets y
     */
    @Test
    public void testGetY() {
        int initialX = 60;
        int initialY = 70;
        EnemyMovementStrategy movementStrategy = enemy -> { };
        BlueEnemy blueEnemy = new BlueEnemy(null, initialX, initialY, movementStrategy);
        int y = blueEnemy.getY();
        assertEquals(initialY, y);
    }
}
