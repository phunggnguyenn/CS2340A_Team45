package com.example.test;

import com.example.model.GreenEnemyMovementStrategy;
import com.example.model.BlueEnemyMovementStrategy;
import com.example.model.EnemyMovementStrategy;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EnemyTest {

    @Test
    public void testGreenEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy greenEnemyMovementStrategy = new GreenEnemyMovementStrategy();
        assertTrue(greenEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }
    @Test
    public void testBlueEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy blueEnemyMovementStrategy = new BlueEnemyMovementStrategy();
        assertTrue(blueEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }

}
