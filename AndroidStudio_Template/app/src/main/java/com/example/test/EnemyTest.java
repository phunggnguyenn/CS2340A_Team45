package com.example.test;

import com.example.model.GreenEnemyMovementStrategy;
import com.example.model.BlueEnemyMovementStrategy;
import com.example.model.EnemyMovementStrategy;
import com.example.model.WhiteEnemyMovementStrategy;
import com.example.model.YellowEnemyMovementStrategy;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

import android.widget.ImageView;

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
    /**
     * Member: Maria Jothish
     * This method tests if yellow enemy implements strategy
     */
    @Test
    public void testYellowEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy yellowEnemyMovementStrategy = new YellowEnemyMovementStrategy();
        assertTrue(yellowEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }
    /**
     * Member: Maria Jothish
     * This method tests if white enemy implements strategy
     */
    @Test
    public void testWhiteEnemyImplementsEnemyMovementStrategy() {
        EnemyMovementStrategy whiteEnemyMovementStrategy = new WhiteEnemyMovementStrategy();
        assertTrue(whiteEnemyMovementStrategy instanceof EnemyMovementStrategy);
    }
}
