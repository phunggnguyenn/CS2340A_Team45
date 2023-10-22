package com.example.test;

import com.example.model.Player;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class PlayerTest {
    private Player player1;
    private Player player2;
    @Before
    public void setUp() {
        player1 = Player.getInstance("Player 1", 100, 1, 5, 6);
        player2 = Player.getInstance("Player 2", 200, 2, 5, 6);
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

    // Member: Edmond Li
    // This method tests if 0 player health remains 0
    @Test
    public void testNoHealth() {
        player1.setHealthPoints(0);
        assertEquals(0, player1.getHealthPoints());
    }
    // Member: Edmond Li
    // This method tests if negative player health becomes 0
    @Test
    public void testNegativeHealth() {
        player1.setHealthPoints(-100);
        assertEquals(0, player1.getHealthPoints());
    }
    // Member: Mahathi Gumudavelli
    // This method tests if player moves up appropriate amount at start.
    @Test
    public void testMoveUpAtStart() {
        int oldY = player1.getY();
        player1.moveUp();
        assertEquals(oldY - 10, player1.getY());
    }
    // Member: Mahathi Gumudavelli
    // This method tests if player moves down appropriate amount.
    @Test
    public void testMoveDown() {
        int oldY = player1.getY();
        player1.moveUp();
        player1.moveDown();
        assertEquals(oldY, player1.getY());
    }
    /**
    // Member: Maria Jothish
    // This method tests if player moves left appropriate amount.
    @Test
    public void testMoveLeft() {
        int oldX = player1.getX();
        player1.moveUp();
        player1.moveLeft();
        assertEquals(oldX - 10, player1.getX());
    }
    // Member: Maria Jothish
    // This method tests if player moves right appropriate amount.
    @Test
    public void testMoveRight() {
        int oldX = player1.getX();
        player1.moveUp();
        player1.moveRight();
        assertEquals(oldX + 10, player1.getX());
    }
    // Member: Maria Jothish (CHANGE NAME)
    // This method tests if player moving farther that screen height is a valid move.
    @Test
    public void testIsInvalidMoveOutOfHeight() {
        assertFalse(player1.isValidMove(new ArrayList<>(), 0 , 1170));
    }
    // Member: Maria Jothish (CHANGE NAME)
    // This method tests if player moving wider that screen width is a valid move.
    @Test
    public void testIsInvalidMoveOutOfWidth() {
        assertFalse(player1.isValidMove(new ArrayList<>(), 990 , 0));
    }
    // Member: Maria Jothish (CHANGE NAME)
    // This method tests if player moves within screen width and height is a valid move.
    @Test
    public void testIsValidMove() {
        assertTrue(player1.isValidMove(new ArrayList<>(), 900, 1000));
    }
    */

}
