package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import com.example.model.Leaderboard;


import org.junit.Test;

import java.util.List;

public class MyAppTest {
    private Leaderboard leaderboard;
    public void setUp() {
        leaderboard = leaderboard.getInstance();
    }
    @Test
    /**
     * Member: PHUNG NGUYEN
     * Test checking that Leaderboard updates the descending order of scores
     * when a new score is added.
     * Sprint 2 Junit Test for Leaderboard. Disregard for Sprint 3 & 4.
     */
    public void testAddScores() {
        leaderboard.addScores(300);
        leaderboard.addScores(500);
        leaderboard.addScores(100);

        List<Integer> topScores = leaderboard.getTopScores(3);

        // Check if the scores are in descending order
        assertEquals(3, topScores.size());
        assertTrue(topScores.get(0) >= topScores.get(1));
        assertTrue(topScores.get(1) >= topScores.get(2));
    }
    @Test
    /**
     * Member: PHUNG NGUYEN
     * Test checking that Leaderboard retrieves the top scores.
     */
    public void testGetTopScores() {
        leaderboard.addScores(100);
        leaderboard.addScores(75);
        leaderboard.addScores(120);

        List<Integer> topScores = leaderboard.getTopScores(3);

        // Check if the top scores are correctly retrieved
        assertEquals(3, topScores.size());
        assertEquals(120, topScores.get(0).intValue());
        assertEquals(100, topScores.get(1).intValue());
        assertEquals(75, topScores.get(2).intValue());
    }
}

