package com.example.test;

import com.example.viewmodels.InitialConfigViewModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InitialConfigTest {
    private InitialConfigViewModel viewModel;
    @Before
    public void setUp() {
        viewModel = new InitialConfigViewModel();
    }
    // Member: Edmond Li
    // Test checking set player name functionality
    @Test
    public void testSetPlayerName() {
        String playerName = "JohnDoe";
        viewModel.setPlayerName(playerName);
        assertEquals(playerName, viewModel.getPlayerName());
    }
}
