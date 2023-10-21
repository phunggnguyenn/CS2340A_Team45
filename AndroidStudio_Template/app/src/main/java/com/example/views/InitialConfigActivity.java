package com.example.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.demo_2340.R;
import com.example.viewmodels.InitialConfigViewModel;

public class InitialConfigActivity extends AppCompatActivity {
    private InitialConfigViewModel viewModel = new InitialConfigViewModel();

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
        viewModel.handleAvatarSelection(avatar2); // default selection

        avatar1.setOnClickListener(v -> viewModel.handleAvatarSelection(avatar1));
        avatar2.setOnClickListener(v -> viewModel.handleAvatarSelection(avatar2));
        avatar3.setOnClickListener(v -> viewModel.handleAvatarSelection(avatar3));

        EditText editTextName = findViewById(R.id.editTextName);
        RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        Button continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(v -> {
            String playerName = editTextName.getText().toString().trim();
            int selectedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();
            // Display an error message if not all fields filled out
            if (playerName.isEmpty() || selectedRadioButtonId == -1) {
                Toast.makeText(InitialConfigActivity.this,
                        "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.setPlayerName(playerName);
                viewModel.getDifficultyFromRadioButton(selectedRadioButtonId);
                // You can start the game activity with the player's name and difficulty level.
                if (viewModel.getSelectedAvatar() != null) {
                    startGameActivity(playerName, viewModel.getDifficulty(),
                            viewModel.getSelectedAvatar());
                }
            }
        });
    }

    private void startGameActivity(String playerName, double difficulty, ImageView selectedAvatar) {
        Intent gameIntent = new Intent(this, MazeGameActivity.class);
        gameIntent.putExtra("playerName", playerName);
        gameIntent.putExtra("difficulty", difficulty);
        gameIntent.putExtra("avatar", selectedAvatar.getId());
        startActivity(gameIntent);
        finish(); // Finish the initial config activity
    }

}