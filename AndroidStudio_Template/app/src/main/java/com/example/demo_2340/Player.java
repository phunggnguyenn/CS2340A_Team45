package com.example.demo_2340;

import java.io.Serializable;


public class Player implements Serializable{
    private String playerName;
    private int healthPoints;
    private int avatarId;

    public Player(String playerName, int healthPoints, int avatarId) {
        this.playerName = playerName;
        this.healthPoints = healthPoints;
        this.avatarId = avatarId;
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