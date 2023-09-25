package com.example.demo_2340;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class InitialConfigActivity extends AppCompatActivity {
    private ImageView selectedAvatar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_config);

        // Initialize avatar images and set opacity to 30%
        ImageView avatar1 = findViewById(R.id.imageAvatar1);
        avatar1.setAlpha(0.3f);
        ImageView avatar2 = findViewById(R.id.imageAvatar2);
        avatar2.setAlpha(0.3f);
        ImageView avatar3 = findViewById(R.id.imageAvatar3);
        avatar3.setAlpha(0.3f);
        handleAvatarSelection(avatar2); // default selection

        avatar1.setOnClickListener(v -> handleAvatarSelection(avatar1));

        avatar2.setOnClickListener(v -> handleAvatarSelection(avatar2));

        avatar3.setOnClickListener(v -> handleAvatarSelection(avatar3));

        EditText editTextName = findViewById(R.id.editTextName);
        RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        Button continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = editTextName.getText().toString().trim();
                int selectedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();
                // Display an error message if not all fields filled out
                if (playerName.isEmpty() || selectedRadioButtonId == -1) {
                    Toast.makeText(InitialConfigActivity.this,
                            "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Continue to the game screen with the selected values.
                    double difficulty = getDifficultyFromRadioButton(selectedRadioButtonId);
                    // You can start the game activity with the player's name and difficulty level.
                    startGameActivity(playerName, difficulty);
                }
            }
        });
    }

    private double getDifficultyFromRadioButton(int radioButtonId) {
        switch (radioButtonId) {
        case R.id.radioEasy: return 0.5;
        case R.id.radioMedium: return 0.75;
        case R.id.radioHard: return 1;
        default: return 0.5; // Default difficulty
        }
    }

    private void startGameActivity(String playerName, double difficulty) {
        Intent gameIntent = new Intent(this, MazeGameActivity.class);
        gameIntent.putExtra("playerName", playerName);
        gameIntent.putExtra("difficulty", difficulty);
        startActivity(gameIntent);
        finish(); // Finish the initial config activity
    }

    private void handleAvatarSelection(ImageView avatar) {
        if (selectedAvatar != null) {
            // Deselect the previously selected avatar
            selectedAvatar.setAlpha(0.3f); // Restore opacity to 30%
        }
        // Select the clicked avatar
        selectedAvatar = avatar;
        avatar.setAlpha(1.0f); // Set opacity to 100% to indicate selection
    }
}