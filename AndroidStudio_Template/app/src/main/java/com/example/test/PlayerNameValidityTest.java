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
        player = Player.getInstance("PlayerName", 100, 1, 5, 6);
    }

    //MEMBER: MAHATHI GUMUDAVELLI
    //THIS METHOD TESTS When playername is empty
    @Test
    public void testPlayerNameIsEmpty() {
        player = Player.getInstance("PlayerName", 100, 1, 5, 6);
        assertFalse("Player Name is empty!", player.getPlayerName().isEmpty());
    }
    //MEMBER: MAHATHI GUMUDAVELLI
    //THIS METHOD TESTS When playername is null
    @Test
    public void testPlayerNameIsNull() {
        player = Player.getInstance("PlayerName", 100, 1, 5, 6);
        assertFalse("Player Name is Null!", player.getPlayerName() == null);
    }
}