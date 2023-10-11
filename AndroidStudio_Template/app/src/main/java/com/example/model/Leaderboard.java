package com.example.model;


import com.example.views.GameEndActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A leaderboard implementation that uses Singleton design pattern.
 * This leaderboard should display scores in descending order and
 * display the top attempts made by the player.
 */
public class Leaderboard extends GameEndActivity {
    // List that keeps a history of previous scores
    private ArrayList<Integer> scoresList;
    private static Leaderboard leaderboard;

    /*
     * Constructor for Leaderboard
     * Initialize scores to an empty ArrayList
     */
    private Leaderboard() {
        this.scoresList = new ArrayList<>();
    }

    /*
     * Returns the instance of the leaderboard
     * @return the instance of the leaderboard
     */
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard();
        }
        return leaderboard;
    }
    public void addScores(int score) {

        scoresList.add(score);

        // Sort the scores in descending order
        Collections.sort(scoresList, Collections.reverseOrder());
    }

    /**
     * Getter method to retrieve top attempts made by the player.
     *
     * @param count the top score.
     * @return the top scores of player.
     */
    public List<Integer> getTopScores(int count) {
        return scoresList.subList(0, Math.min(count, scoresList.size()));
    }
}