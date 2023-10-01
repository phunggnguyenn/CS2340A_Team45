package com.example.demo_2340;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RoomOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        RelativeLayout room1Layout = findViewById(R.id.room1Layout);
        Intent receiverIntent = getIntent();
        String playerName = receiverIntent.getStringExtra("playerName");
        Double receivedDifficulty = receiverIntent.getDoubleExtra("difficulty", 0.75);
        // tile dimensions
        int tileWidth = 80;
        int tileHeight = 80;

        // # rows and cols in room3 grid
        int numRows = 14;
        int numColumns = 12;

        int margin = 10;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {

                int left = col * (tileWidth + margin);
                int top = row * (tileHeight + margin);

                ImageView tilesImageView = new ImageView(this);
                if ((row == 0 && ((col < 8) || (col > 8)))
                    || (row == 1 && col < 1)
                    || (row == 2 && ((col < 2) || (col > 4 && col < 11)))
                    || (row == 3 && col == 9)
                    || (row == 4 && (col < 8))
                    || (row == 5 && ((col == 1) || (col == 3) || (col == 6) || (col > 8 && col < 11)))
                    || (row == 6 && ((col == 6) || (col == 10)))
                    || (row == 7 && (((col > 2) && (col < 5)) || (col == 10)))
                    || (row == 8 && ((col > 1 && col < 4) || (col > 5 && col < 9)))
                    || (row == 9 && ((col > 2) && (col < 5) || col == 6 || col == 8 || col == 11))
                    || (row == 10 && col == 11)
                    || (row == 11 && ((col > 0 && col < 4) || (col > 4 && col < 8)))
                    || (row == 13 && col!= 5)) {
                    tilesImageView.setImageResource(R.drawable.blacktile3);
                } else {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams = new RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room1Layout.addView(tilesImageView, redTilesParams);
            }
        }
        Button room2btn = findViewById(R.id.room2btn);
        room2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRoom2Activity(playerName, receivedDifficulty);
            }
        });
    }
    private void startRoom2Activity(String playerName, double receivedDifficulty) {
        Intent room2Intent = new Intent(this, RoomTwo.class);
        room2Intent.putExtra("playerName", playerName);
        room2Intent.putExtra("difficulty", receivedDifficulty);
        startActivity(room2Intent);
        finish(); // Finish the room1 activity
    }
}

