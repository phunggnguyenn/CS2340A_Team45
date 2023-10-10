package com.example.demo_2340;

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private int score;

    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        if (this.getScore() > other.getScore()) {
            return -1; // Negative value indicates the current score is greater (descending order).
        } else if (this.getScore() < other.getScore()) {
            return 1; // Positive value indicates the other score is greater.
        } else {
            return 0; // Scores are equal.
        }
    }

    public int getScore() {
        return score;
    }
}
