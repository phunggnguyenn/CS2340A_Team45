package com.example.test;
import com.example.model.Player;
import com.example.viewmodels.CollisionObserver;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class PowerUpTest {
    private Player player;
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
}
