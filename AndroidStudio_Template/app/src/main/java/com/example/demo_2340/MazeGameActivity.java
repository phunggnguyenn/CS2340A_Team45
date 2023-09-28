package com.example.demo_2340;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private int healthPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze); //will use the activity_maze xml background
        Intent receiverIntent = getIntent();
        textView = (TextView) findViewById(R.id.difficultyreciever);
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
        RelativeLayout layout = findViewById(R.id.RelativeLayoutId);

        ImageView characterImageView = new ImageView(this);
        characterImageView.setImageResource(getCharacterImageResource(avatarId));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                300, 300);
        params.topMargin = 500;
        characterImageView.setLayoutParams(params);
        characterImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Add the character
        layout.addView(characterImageView);


        Button exitButton2 = findViewById(R.id.exitButton2);
        exitButton2.setOnClickListener(v -> {
            // Button to ending screen
            setContentView(R.layout.activity_game_end);
        });
    }
    private int getCharacterImageResource(int avatarId) {
        switch (avatarId) {
        case R.id.imageAvatar1: return R.drawable.avatar1;
        case R.id.imageAvatar2: return R.drawable.avatar2;
        case R.id.imageAvatar3: return R.drawable.avatar3;
        default: return R.drawable.avatar1;
        }
    }
}
