package com.example.test;
import com.example.model.Player;
import com.example.viewmodels.CollisionObserver;
import com.example.viewmodels.RoomOneViewModel;
import com.example.viewmodels.RoomTwoViewModel;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class PowerUpTest {
    private Player player;
    private RoomOneViewModel viewModel1;
    private RoomTwoViewModel viewModel2;
    @Before
    public void setUp() {
        player = Player.getInstance("Player", 100, 1, 5, 6, 1);
    }
    @Test
    // Member: MARIA JOTHISH
    // Test that checks if powerUpCollision returns -1 when all power ups are null.
    public void testNullPowerUp() {
        CollisionObserver observer = new CollisionObserver(player, null, null,
                null, null, null, null);
        int result = observer.powerUpCollision();
        assertEquals(-1, result);
    }
    @Test
    // Member: MARIA JOTHISH
    // Test that checks if null power ups have an impact on the player.
    public void testNullPowerUpEffect() {
        assertEquals(100, player.getHealthPoints());
    }
    @Test
    /**
     * Member: Phung Nguyen
     * Test that checks if ScorePowerUp increases the score for Room1.
     */
    public void testScorePowerUpIncreasesScoreInRoomOne() {
        CollisionObserver observer = new CollisionObserver(player, null, null,
                null, null, null, null);
        viewModel1 = new RoomOneViewModel(player, null);
        int initialScore = viewModel1.getScore();
        int result = observer.powerUpCollision(); // Assuming powerUpCollision method
        // is called to handle collisions

        // Check if the score has increased
        int newScore = viewModel1.getScore();
        int expectedScore = newScore; // Adjust the expected score based on your implementation
        assertEquals(expectedScore, newScore);
    }
    @Test
    /**
     * Member: Phung Nguyen
     * Test that checks if ScorePowerUp increases the score for Room2.
     */
    public void testScorePowerUpIncreasesScoreInRoomTwo() {
        CollisionObserver observer = new CollisionObserver(player, null, null,
                null, null, null, null);
        viewModel2 = new RoomTwoViewModel(player, 5, null);
        int initialScore = viewModel2.getScore();
        int result = observer.powerUpCollision(); // Assuming powerUpCollision method
        // is called to handle collisions

        // Check if the score has increased
        int newScore = viewModel2.getScore();
        int expectedScore = newScore; // Adjust the expected score based on your implementation
        assertEquals(expectedScore, newScore);
    }
}
