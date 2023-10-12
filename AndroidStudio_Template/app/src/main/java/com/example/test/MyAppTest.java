package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import android.content.Intent;
import android.os.Bundle;

import com.example.model.Leaderboard;
import com.example.views.MazeGameActivity;
import com.example.views.RoomOne;
import com.example.views.RoomTwo;

import org.junit.Test;

import java.util.List;

public class MyAppTest {
    @Test
    //Member: MAHATHI GUMUDAVELLI
    //this method is supposed to check if the player name is valid (no null, white space, empty)
    public void testPlayerName() {
        // Creating an instance of MazeGameActivity
        MazeGameActivity mazeGameActivity = new MazeGameActivity();

        // Testing empty player name
        Intent testIntent2 = new Intent();
        testIntent2.putExtra("playerName", "");
        testIntent2.putExtra("difficulty", 0.75);

        Bundle savedInstanceState2 = new Bundle();
        savedInstanceState2.putParcelable("intent", testIntent2);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState2);

        assertFalse(mazeGameActivity.getTextView3().getText() != null
                && !mazeGameActivity.getTextView3().getText().toString().trim().isEmpty());

        //Testing white space player name
        Intent testIntent3 = new Intent();
        testIntent3.putExtra("playerName", " ");
        testIntent3.putExtra("difficulty", 0.6);

        Bundle savedInstanceState3 = new Bundle();
        savedInstanceState3.putParcelable("intent", testIntent3);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState3);

        assertFalse(mazeGameActivity.getTextView3().getText() != null
                && !mazeGameActivity.getTextView3().getText().toString().trim().isEmpty());

        // Testing null player name
        Intent testIntent4 = new Intent();
        String playerName = null;
        testIntent4.putExtra("playerName", playerName);
        testIntent4.putExtra("difficulty", 0.5);

        Bundle savedInstanceState4 = new Bundle();
        savedInstanceState4.putParcelable("intent", testIntent4);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState4);

        assertFalse(mazeGameActivity.getTextView3().getText() != null
                && !mazeGameActivity.getTextView3().getText().toString().trim().isEmpty());
    }

    @Test
    //Member: MAHATHI GUMUDAVELLI
    //This method's for checking if its assigning correct HP according to difficulty level
    public void testHealthPointsBasedOnDifficulty() {
        MazeGameActivity mazeGameActivity = new MazeGameActivity();

        Intent testIntent1 = new Intent();
        testIntent1.putExtra("playerName", "TestPlayer");
        testIntent1.putExtra("difficulty", 1.0);

        //onCreate
        Bundle savedInstanceState1 = new Bundle();
        savedInstanceState1.putParcelable("intent", testIntent1);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState1);

        //storing healthPoints var
        int actualHealthPoints1 = mazeGameActivity.getHP();
        //checking if 100 points for difficulty level 1.0
        assertEquals(100, actualHealthPoints1);

        //Doing same for 0.75 DL
        Intent testIntent2 = new Intent();
        testIntent2.putExtra("playerName", "TestPlayer");
        testIntent2.putExtra("difficulty", 0.75);

        Bundle savedInstanceState2 = new Bundle();
        savedInstanceState2.putParcelable("intent", testIntent2);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState2);

        int actualHealthPoints075 = mazeGameActivity.getHP();
        assertEquals(125, actualHealthPoints075);

        //Doing same for Easy DL
        Intent testIntent3 = new Intent();
        testIntent3.putExtra("playerName", "TestPlayer");
        testIntent3.putExtra("difficulty", 0.6);

        Bundle savedInstanceState3 = new Bundle();
        savedInstanceState2.putParcelable("intent", testIntent3);
        mazeGameActivity.publicOnCreateWrapper(savedInstanceState3);

        int actualHealthPoints3 = mazeGameActivity.getHP();
        assertEquals(125, actualHealthPoints3);
    }
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

