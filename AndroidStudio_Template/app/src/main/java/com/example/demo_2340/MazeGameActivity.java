package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
     TextView textView;
     TextView textView2;
    TextView textView3;
    int healthPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game); //will use the activity_initial_game xml
        Intent receiverIntent = getIntent();
        textView = (TextView) findViewById(R.id.difficultyreceiver);
        textView2 = (TextView) findViewById(R.id.healthpts);

        int avatarId = receiverIntent.getIntExtra("selectedAvatarId", R.id.imageAvatar2);
        String playerName = receiverIntent.getStringExtra("playerName");
        textView3 = (TextView) findViewById(R.id.playerName);
        textView3.setText("Player Name: " + playerName);

        Double receivedDifficulty = receiverIntent.getDoubleExtra("difficulty", 0.75);
        if (receivedDifficulty == 1.0) {
            textView.setText("Difficulty: Hard");
            healthPoints = 100;
            textView2.setText("HP: 100");
        } else if (receivedDifficulty == 0.75) {
            textView.setText("Difficulty: Medium");
            healthPoints = 125;
            textView2.setText("HP: 125");
        } else {
            textView.setText("Difficulty: Easy");
            healthPoints = 150;
            textView2.setText("HP: 150");
        }
        /** LinearLayout layout = findViewById(R.id.LinearLayoutId);

        ImageView characterImageView = new ImageView(this);
        characterImageView.setImageResource(getCharacterImageResource(avatarId));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                300, 300);
        params.topMargin = 500;
        characterImageView.setLayoutParams(params);
        characterImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Add the character
        layout.addView(characterImageView);
        */

        Button room1btn = findViewById(R.id.room1btn);
        room1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom1Activity(playerName, receivedDifficulty);
            }
        });
    }
    private void startRoom1Activity(String playerName, double receivedDifficulty) {
        Intent room1Intent = new Intent(this, RoomOne.class);
        room1Intent.putExtra("playerName", playerName);
        room1Intent.putExtra("difficulty", receivedDifficulty);
        startActivity(room1Intent);
        finish(); // Finish the initial_game activity
    }
//methods to call these vars from test package
    public TextView getTextView3() {
        return textView3;
    }
    public int getHP() {
        return healthPoints;
    }
    public void publicOnCreateWrapper(Bundle savedInstanceState) {
        onCreate(savedInstanceState);
    }
    /**
    private int getCharacterImageResource(int avatarId) {
        switch (avatarId) {
        case R.id.imageAvatar1: return R.drawable.avatar1;
        case R.id.imageAvatar2: return R.drawable.avatar2;
        case R.id.imageAvatar3: return R.drawable.avatar3;
        default: return R.drawable.avatar1;
        }
    }
     */
}
