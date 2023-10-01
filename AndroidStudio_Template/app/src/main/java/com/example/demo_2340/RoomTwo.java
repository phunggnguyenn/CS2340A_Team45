package com.example.demo_2340;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class RoomTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        RelativeLayout room2Layout = findViewById(R.id.room2Layout);

        // tile dimensions
        int tileWidth = 80;
        int tileHeight = 80;

        // # rows and cols in room3 grid
        int numRows = 14;
        int numColumns = 13;

        int margin = 10;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {

                int left = col * (tileWidth + margin);
                int top = row * (tileHeight + margin);

                ImageView tilesImageView = new ImageView(this);
                if ((row%4==1 || col%5==1) || (row%4==2 && col%4==0) || (row%4==3 && col%4==0)
                        || (row%3==2 && col%4==3 )|| (row%3==1 && col%5==6) || (col%9==0 && row%5==0)) {
                    tilesImageView.setImageResource(R.drawable.red_tile);
                } else {
                    tilesImageView.setImageResource(R.drawable.blacktile2);
                }
                tilesImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                RelativeLayout.LayoutParams redTilesParams = new RelativeLayout.LayoutParams(tileWidth, tileHeight);
                redTilesParams.leftMargin = left;
                redTilesParams.topMargin = top;

                room2Layout.addView(tilesImageView, redTilesParams);
            }
        }
    }
}

