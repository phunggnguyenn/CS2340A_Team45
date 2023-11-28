package com.example.model;

import java.util.Comparator;


/**
 * ScoreEntry class implements a Comparator
 * and passes it to Collections.sort() in Leaderboard class.
 * This leaderboard should display scores in descending order based
 * on the implementation of the ScoreEntry class.
 */
public class ScoreEntry implements Comparator<Integer> {
    @Override
    public int compare(Integer score1, Integer score2) {
        return score2 - score1;
    }
}
