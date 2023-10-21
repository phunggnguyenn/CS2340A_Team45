package com.example.viewmodels;

import android.widget.ImageView;
import com.example.demo_2340.R;


public class InitialConfigViewModel {
    private double difficulty;
    private ImageView selectedAvatar;
    private String playerName;

    public void handleAvatarSelection(ImageView avatar) {
        if (selectedAvatar != null) {
            // Deselect the previously selected avatar
            selectedAvatar.setAlpha(0.3f); // Restore opacity to 30%
        }
        // Select the clicked avatar
        selectedAvatar = avatar;
        avatar.setAlpha(1.0f); // Set opacity to 100% to indicate selection
    }

    public void getDifficultyFromRadioButton(int radioButtonId) {
        switch (radioButtonId) {
        case R.id.radioEasy:
            difficulty = 0.5;
            break;
        case R.id.radioMedium:
            difficulty = 0.75;
            break;
        case R.id.radioHard:
            difficulty = 1;
            break;
        default:
            break;
        }
    }
    public double getDifficulty() {
        return difficulty;
    }
    public ImageView getSelectedAvatar() {
        return selectedAvatar;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}