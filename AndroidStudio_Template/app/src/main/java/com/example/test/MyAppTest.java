package com.example.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
import android.content.Intent;
import android.os.Bundle;

import com.example.demo_2340.MazeGameActivity;
import com.example.demo_2340.RoomOne;
import com.example.demo_2340.RoomTwo;

import org.junit.Test;

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
        String playername = null;
        testIntent4.putExtra("playerName", playername);
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
}

