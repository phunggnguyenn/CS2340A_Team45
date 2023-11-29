package com.example.test;

import com.example.model.Player;
import com.example.viewmodels.CollisionObserver;
import com.example.viewmodels.RoomOneViewModel;


import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class ScoreTest {
    private Player player;
    private RoomOneViewModel viewModel;
    private CollisionObserver collisionObserver;

    @Before
    public void setUp() {
        player = Player.getInstance("Player", 100, 1, 5, 6, 1);
        viewModel = new RoomOneViewModel(player, null);
        CollisionObserver observer = new CollisionObserver(player, null, null,
                null, null, null, null);
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

    @Test
    // Member: Jaeung Woo
    // Test right at the edge of valid input values.
    public void testScoreBoundaryCondition() {
        viewModel.updateScore(Integer.MAX_VALUE - 1000);
        assertEquals(Integer.MAX_VALUE, viewModel.getScore());

        viewModel.updateScore(Integer.MIN_VALUE);
        assertEquals(0, viewModel.getScore());
    }

    @Test
    // Member: Jaeung Woo
    // Test that updating the score by zero leaves it unchanged.
    public void testScoreUpdateZero() {
        viewModel.updateScore(0);
        assertEquals(1000, viewModel.getScore());
    }

}

