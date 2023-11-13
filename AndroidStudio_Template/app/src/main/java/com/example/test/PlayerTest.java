package com.example.test;

import com.example.model.Leaderboard;
import com.example.model.Player;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;

import android.view.KeyEvent;

public class PlayerTest {
    private Player player1;
    private Player player2;
    private Leaderboard leaderboard;


    @Before
    public void setUp() {
        player1 = Player.getInstance("Player 1", 100, 1, 5, 6, 1);
        player2 = Player.getInstance("Player 2", 200, 2, 5, 6, 1);

    }
    // Member: Jaeung Woo
    // This method tests setting Player's goal location
    @Test
    public void testSetGoal() {
        player1.setGoalX(100);
        player1.setGoalY(200);
        assertEquals(100, player1.getGoalX());
        assertEquals(200, player1.getGoalY());
    }
    // Member: Jaeung Woo
    // This method tests player movement
    @Test
    public void testMovement() {
        player1.setX(100);
        player1.setY(100);
        player1.move(KeyEvent.KEYCODE_DPAD_DOWN);
        assertEquals(110, player1.getY());
        player1.move(KeyEvent.KEYCODE_DPAD_UP);
        assertEquals(100, player1.getY());
        player1.move(KeyEvent.KEYCODE_DPAD_LEFT);
        assertEquals(90, player1.getX());
        player1.move(KeyEvent.KEYCODE_DPAD_RIGHT);
        assertEquals(100, player1.getX());
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
        assertEquals(1, player1.getAvatarId());
        // Player 2 will be the same due to Singleton
        assertEquals("Player 1", player2.getPlayerName());
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
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if player moves up appropriate amount at start.
     */
    @Test
    public void testMoveUpAtStartMethod() {
        int oldY = player1.getY();
        player1.moveUp();
        assertEquals(oldY - 10, player1.getY());
    }
    /**
     * Member: Mahathi Gumudavelli
     * This method tests if player moves down appropriate amount.
     */
    @Test
    public void testMoveDownMethod() {
        int oldY = player1.getY();
        player1.moveUp();
        player1.moveDown();
        assertEquals(oldY, player1.getY());
    }


    /**
     * Member: Maria Jothish
     * This method tests if player moves left appropriate amount.
     */
    @Test
    public void testMoveLeftMethod() {
        int oldX = player1.getX();
        player1.moveUp();
        player1.moveLeft();
        assertEquals(oldX - 10, player1.getX());
    }
    /**
     * Member: Maria Jothish
     * This method tests if player moves right appropriate amount.
     */
    @Test
    public void testMoveRightMethod() {
        int oldX = player1.getX();
        player1.moveUp();
        player1.moveRight();
        assertEquals(oldX + 10, player1.getX());
    }
    // Member: Phung Nguyen
    // This method tests if player moving farther that screen height is a valid move.
    @Test
    public void testIsInvalidMoveOutOfHeight() {
        assertFalse(player1.isValidMove(new ArrayList<>(), 0, 1170));
        assertFalse(player1.isValidMove(new ArrayList<>(), 0, -1));
    }
    // Member: Phung Nguyen
    // This method tests if player moving wider that screen width is a valid move.
    @Test
    public void testIsInvalidMoveOutOfWidth() {
        assertFalse(player1.isValidMove(new ArrayList<>(), 990, 0));
        assertFalse(player1.isValidMove(new ArrayList<>(), -1, 0));
    }
    // Member: Jaeung Woo
    // This method tests if player moves within screen width and height is a valid move.
    @Test
    public void testIsValidMove() {
        assertTrue(player1.isValidMove(new ArrayList<>(), 900, 1000));
    }
    // Member: Jaeung Woo
    // This method tests what happens when KeyEventUp occurs.
    @Test
    public void testKeyCodeUp() {
        int oldY = player1.getY();
        player1.move(KeyEvent.KEYCODE_DPAD_UP);
        assertEquals(oldY - 10, player1.getY());
    }
    // Member: Ellie Salisbury
    // This method tests what happens when KeyEventDown occurs.
    @Test
    public void testKeyCodeDown() {
        int oldY = player1.getY();
        player1.move(KeyEvent.KEYCODE_DPAD_UP);
        player1.move(KeyEvent.KEYCODE_DPAD_DOWN);
        assertEquals(oldY, player1.getY());
    }
    // Member: Ellie Salisbury
    // This method tests what happens when KeyEventLeft occurs.
    @Test
    public void testKeyCodeLeft() {
        int oldX = player1.getX();
        player1.move(KeyEvent.KEYCODE_DPAD_UP);
        player1.move(KeyEvent.KEYCODE_DPAD_LEFT);
        assertEquals(oldX - 10, player1.getX());
    }
    // Member: Edmond Li
    // This method tests what happens when KeyEventRight occurs.
    @Test
    public void testKeyCodeRight() {
        int oldX = player1.getX();
        player1.move(KeyEvent.KEYCODE_DPAD_UP);
        player1.move(KeyEvent.KEYCODE_DPAD_RIGHT);
        assertEquals(oldX + 10, player1.getX());
    }

    /**
     * Member: EDMOND LI
     * Test if player height value is properly stored
     */
    @Test
    public void testHeight() {
        assertEquals( 6, player1.getPlayerHeight());
    }

    /**
     * Member: EDMOND LI
     * Test if player width value is properly stored
     */
    @Test
    public void testWidth() {
        assertEquals(5, player1.getPlayerWidth());
    }

}
