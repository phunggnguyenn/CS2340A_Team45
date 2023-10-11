package com.example.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Player;
import com.example.demo_2340.R;

//launchMazeGameActivity() method in GameActivity.java will launch the maze background
public class MazeGameActivity extends AppCompatActivity {
     TextView textView;
     TextView textView2;
    TextView textView3;
    int healthPoints;
    private int avatarId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_game); //will use the activity_initial_game xml
        Intent receiverIntent = getIntent();
        textView = (TextView) findViewById(R.id.difficultyreceiver);
        textView2 = (TextView) findViewById(R.id.healthpts);

        int avatar = receiverIntent.getIntExtra("avatar", R.id.imageAvatar2);
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
        ImageView avatarImage = findViewById(R.id.imageAvatar);
        switch (avatar) {
            case R.id.imageAvatar1:
                avatarId = R.drawable.avatar1;
                avatarImage.setImageResource(R.drawable.avatar1);
                break;
            case R.id.imageAvatar2:
                avatarId = R.drawable.avatar2;
                avatarImage.setImageResource(R.drawable.avatar2);
                break;
            case R.id.imageAvatar3:
                avatarId = R.drawable.avatar3;
                avatarImage.setImageResource(R.drawable.avatar3);
                break;
        }
        // create player class
        Player player = Player.getInstance(playerName, healthPoints, avatarId);


        Button room1btn = findViewById(R.id.room1btn);
        room1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom1Activity(player);
            }
        });
    }
    private void startRoom1Activity(Player player) {
        Intent room1Intent = new Intent(this, RoomOne.class);
        room1Intent.putExtra("player", player);
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
}
