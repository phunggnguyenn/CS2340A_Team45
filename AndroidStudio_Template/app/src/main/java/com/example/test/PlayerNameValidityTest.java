package com.example.test;

import org.junit.Before;
import org.junit.Test;
import com.example.model.Player;
import static org.junit.Assert.assertFalse;

public class PlayerNameValidityTest {
    private Player player;
    @Before
    public void setUp() {
        // Create a Player instance for testing
        player = Player.getInstance("PlayerName", 100, 1);
    }
    //MEMBER: MAHATHI GUMUDAVELLI
    //THIS METHOD TESTS When playername is neither null or empty
    @Test
    public void testPlayerNameNotNullOrEmpty() {
        // Assert that the player name is not null or empty
        assertFalse("Player name is not null or empty!", player.getPlayerName()== null || player.getPlayerName().isEmpty());
    }
    //MEMBER: MAHATHI GUMUDAVELLI
    //THIS METHOD TESTS When playername is empty
    @Test
    public void testPlayerNameIsEmpty() {
        player = Player.getInstance("", 100, 1);
        assertFalse("Player Name is not empty!", player.getPlayerName().equals(""));
    }
    //MEMBER: MAHATHI GUMUDAVELLI
    //THIS METHOD TESTS When playername is null
    @Test
    public void testPlayerNameIsNull() {
        player = Player.getInstance(null, 100, 1);
        assertFalse("Player Name is not Null!", player.getPlayerName() == null);
    }
}