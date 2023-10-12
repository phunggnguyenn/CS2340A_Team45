package com.example.model;

import java.io.Serializable;


public class Player implements Serializable{
    private String playerName;
    private int healthPoints;
    private int avatarId;
    private static Player player;

    // Private constructor to prevent instantiation from other classes
    private Player(String playerName, int healthPoints, int avatarId) {
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.avatarId = avatarId;
    }
    public static Player getInstance(String playerName, int healthPoints, int avatarId) {
        if (player == null) {
            player = new Player(playerName, healthPoints, avatarId);
        }
        return player;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else {
            this.healthPoints = healthPoints;
        }
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getAvatarId() {
        return avatarId;
    }
}