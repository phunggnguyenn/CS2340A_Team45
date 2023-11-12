package com.example.test;

import com.example.model.GreenEnemyMovementStrategy;
import com.example.model.BlueEnemyMovementStrategy;
import com.example.model.EnemyMovementStrategy;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EnemyTest {
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if green enemy implements strategy
     */
    @Test
    public void testGreenEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy greenEnemyMovementStrategy = new GreenEnemyMovementStrategy();
        assertTrue(greenEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if blue enemy implements strategy
     */
    @Test
    public void testBlueEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy blueEnemyMovementStrategy = new BlueEnemyMovementStrategy();
        assertTrue(blueEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }

}
