package com.example.model;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


/**
 * ScoreEntry class implements a Comparator
 * and passes it to Collections.sort() in Leaderboard class.
 * This leaderboard should display scores in descending order based
 * on the implementation of the ScoreEntry class.
 */
public class ScoreEntry implements Comparator<Integer>, Player.HealthPointChangeListener {
    private Player player;
    private int score;

    public ScoreEntry() {
        this.player = player;
        player.registerHealthPointChangeListener(this);
        this.score = calculateScore(player.getHealthPoints());
    }

    @Override
    public int compare(Integer score1, Integer score2) {
        return score2 - score1;
    }

    @Override
    public Comparator<Integer> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Integer> thenComparing(Comparator<? super Integer> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Integer> thenComparing(Function<? super Integer, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Integer> thenComparing(Function<? super Integer, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Integer> thenComparingInt(ToIntFunction<? super Integer> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Integer> thenComparingLong(ToLongFunction<? super Integer> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Integer> thenComparingDouble(ToDoubleFunction<? super Integer> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }

    @Override
    public void onHealthPointsChange(int newHealthPoints) {
        int decreaseAmount = player.getHealthPoints();
        score -= decreaseAmount;
        if (score < 0) {
            score = 0;
        }
    }

    @Override
    public int calculateScore(int healthPoints) {
        return healthPoints *= 10;
    }

    public Player getPlayer() {
        return player;
    }
}
