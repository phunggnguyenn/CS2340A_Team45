package com.example.test;

import com.example.model.Player;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player1;
    private Player player2;
    @Before
    public void setUp() {
        player1 = Player.getInstance("Player 1", 100, 1);
        player2 = Player.getInstance("Player 2", 200, 2);
    }
    // Member: Jaeung Woo
    // This method tests if Singleton pattern applies properly
    @Test
    public void testSingleton() {
        // Check if both references point to the same instance
        assertSame(player1, player2);
    }
    // Member: Jaeung Woo
    // This method tests if Singleton pattern applies properly by checking values
    @Test
    public void testValues() {
        // Check if the properties are correctly set
        assertEquals("Player 1", player1.getPlayerName());
        assertEquals(100, player1.getHealthPoints());
        assertEquals(1, player1.getAvatarId());

        // Player 2 will be the same due to Singleton
        assertEquals("Player 1", player2.getPlayerName());
        assertEquals(100, player2.getHealthPoints());
        assertEquals(1, player2.getAvatarId());
    }
}
