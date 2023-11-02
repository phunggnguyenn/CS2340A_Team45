/**
package com.example.model;

import android.os.Handler;
import android.widget.ImageView;

import com.example.demo_2340.R;
import com.example.model.Enemy;

import java.util.Random;

public class GreenEnemy extends Enemy {
    ImageView greenenemy;
    int greenenemy_x = 85;
    int greenenemy_y = 85;
    Handler h = new Handler();

    @Override
    void generate() {
        //Instantiate ImageViews for enemy
        greenenemy = (ImageView) findViewById(R.id.imageGreenEnemy);
        //Set the X and Y coordinate of enemy
        greenenemy.setX(greenenemy_x);
        greenenemy.setY(greenenemy_y);
        move();
    }
    // Start enemy movement
    public void move() {
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                h.postDelayed(this, 100);
                // Call function for enemy movement
                movement();
            }
        }, 1000);
    }
    public void movement() {
        greenenemy_x = greenenemy_x - 10;
        greenenemy.setX(greenenemy_x);
        greenenemy.setY(greenenemy_y);
        if(greenenemy_x == 0) {
            greenenemy_x = 85;
            Random r = new Random();
            greenenemy_y = r.nextInt(800);
        }
    }
}
*/