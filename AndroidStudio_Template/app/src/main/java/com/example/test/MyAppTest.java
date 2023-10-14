package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import com.example.model.Leaderboard;
import com.example.views.MazeGameActivity;
import com.example.views.RoomOne;
import com.example.views.RoomTwo;

import org.junit.Test;

import java.util.List;

public class MyAppTest {

    @Test
    //Member: MARIA JOTHISH
    //Test checking if score is always greater than 0.
    public void scoreGreaterThanZero() {
        RoomOne roomOne = new RoomOne();
        assertEquals(1000, roomOne.getScore());
        roomOne.updateScore(-2000);
        assertTrue(roomOne.getScore() >= 0);
    }
    @Test
    //Member: MARIA JOTHISH
    //Test checking if room1 ending score is equal to room 2 starting score.
    public void scoreTransferR1toR2() {
        RoomOne roomOne = new RoomOne();
        RoomTwo roomTwo = new RoomTwo();
        roomOne.updateScore(-500);
        int roomOneScore = roomOne.getScore();
        int roomTwoScore = roomTwo.getScore();
        assertEquals(roomOneScore, roomTwoScore);
    }
    private Leaderboard leaderboard;
    public void setUp() {
        leaderboard = leaderboard.getInstance();
    }
    @Test
    /**
     * Member: PHUNG NGUYEN
     * Test checking that Leaderboard updates the descending order of scores
     * when a new score is added.
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

