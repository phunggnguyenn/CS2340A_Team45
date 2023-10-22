package com.example.test;

import com.example.model.Player;
import com.example.viewmodels.RoomOneViewModel;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class ScoreTest {
    private Player player;
    private RoomOneViewModel viewModel;
    @Before
    public void setUp() {
        player = Player.getInstance("Player", 100, 1, 5, 6);
        // viewModel = new RoomOneViewModel(player);
    }

    @Test
    // Member: MARIA JOTHISH
    // Test initial score
    public void testInitialScore() {
        assertEquals(1000, viewModel.getScore());
    }
    @Test
    // Member: MARIA JOTHISH
    // Test checking if score can increase.
    public void testScoreIncrease() {
        viewModel.updateScore(500);
        assertEquals(1500, viewModel.getScore());
    }
    @Test
    // Member: MARIA JOTHISH
    // Test checking if score can decrease.
    public void testScoreDecrease() {
        viewModel.updateScore(-500);
        assertEquals(500, viewModel.getScore());
    }

    @Test
    // Member: MARIA JOTHISH
    // Test checking if score is never below 0.
    public void testScoreNeverBelowZero() {
        viewModel.updateScore(-1500);
        assertEquals(0, viewModel.getScore());
    }
}

