package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class GameEndActivity extends AppCompatActivity {
    private int score;
    private TextView scoreTextView;
    private Leaderboard leaderboard;

    // Get references to the TextViews in XML layout
    TextView[] rankTextViews = new TextView[5];
    TextView[] playerNameTextViews = new TextView[5];
    TextView[] scoreTextViews = new TextView[5];
    TextView[] dateTimeTextViews = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 0);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        leaderboard = Leaderboard.getInstance();

        // Add the current score to the leaderboard
        leaderboard.addScores(score);

        // Display the top scores on the leaderboard
        List<Integer> topScores = leaderboard.getTopScores(5);


        rankTextViews[0] = findViewById(R.id.rank1TextView);
        rankTextViews[1] = findViewById(R.id.rank2TextView);
        rankTextViews[2] = findViewById(R.id.rank3TextView);
        rankTextViews[3] = findViewById(R.id.rank4TextView);
        rankTextViews[4] = findViewById(R.id.rank5TextView);

        playerNameTextViews[0] = findViewById(R.id.player1TextView);
        playerNameTextViews[1] = findViewById(R.id.player2TextView);
        playerNameTextViews[2] = findViewById(R.id.player3TextView);
        playerNameTextViews[3] = findViewById(R.id.player4TextView);
        playerNameTextViews[4] = findViewById(R.id.player5TextView);

        scoreTextViews[0] = findViewById(R.id.score1TextView);
        scoreTextViews[1] = findViewById(R.id.score2TextView);
        scoreTextViews[2] = findViewById(R.id.score3TextView);
        scoreTextViews[3] = findViewById(R.id.score4TextView);
        scoreTextViews[4] = findViewById(R.id.score5TextView);

        dateTimeTextViews[0] = findViewById(R.id.dateTime1TextView);
        dateTimeTextViews[1] = findViewById(R.id.dateTime2TextView);
        dateTimeTextViews[2] = findViewById(R.id.dateTime3TextView);
        dateTimeTextViews[3] = findViewById(R.id.dateTime4TextView);
        dateTimeTextViews[4] = findViewById(R.id.dateTime5TextView);

        for (int i = 0; i < topScores.size(); i++) {
            // Set rank and score
            rankTextViews[i] = findViewById(getResources().getIdentifier("rank" + (i + 1) + "TextView", "id", getPackageName()));
            scoreTextViews[i] = findViewById(getResources().getIdentifier("score" + (i + 1) + "TextView", "id", getPackageName()));
            rankTextViews[i].setText(String.valueOf(i + 1));
            scoreTextViews[i].setText(String.valueOf(topScores.get(i)));
        }
        Button restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartActivity();
            }
        });

    }
    private void restartActivity() {
        recreate(); // restart
        finish();
    }
    public int getScore() {
        return score;
    }
}

