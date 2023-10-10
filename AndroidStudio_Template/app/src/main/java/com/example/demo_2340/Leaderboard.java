package com.example.demo_2340;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A leaderboard implementation that uses Singleton design pattern.
 * This leaderboard should display scores in descending order and
 * display the top attempts made by the player.
 */
public class Leaderboard {
    // List that keeps a history of previous scores
    private ArrayList<ScoreEntry> scores_list;
    private static Leaderboard leaderboard;

    /*
     * Constructor for Shop
     * Initialize scores to an empty ArrayList
     */
    private Leaderboard() {
        this.scores_list = new ArrayList<ScoreEntry>();
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
    public void addScores(String name, int score) {
        ScoreEntry scoreOfPlayer = new ScoreEntry(name, score);
        scores_list.add(scoreOfPlayer);
        Collections.sort(scores_list, Collections.reverseOrder());
    }

    /**
     * Getter method to retrieve top attempts made by the player.
     *
     * @param count the top score.
     * @return the top scores of player.
     */
    public List<ScoreEntry> getTopScores(int count) {
        return scores_list.subList(0, Math.min(count, scores_list.size()));
    }
}