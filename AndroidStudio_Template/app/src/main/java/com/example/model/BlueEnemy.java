/**
package com.example.model;

import android.os.Handler;
import android.widget.ImageView;

import com.example.demo_2340.R;

import java.util.Random;

public class BlueEnemy extends Enemy {
    ImageView blueenemy;
    int blueenemy_x = 715;
    int blueenemy_y = 65;
    Handler h = new Handler();

    @Override
    void generate() {
        //Instantiate ImageViews for enemy
        blueenemy = (ImageView) findViewById(R.id.imageBlueEnemy);
        //Set the X and Y coordinate of enemy
        blueenemy.setX(blueenemy_x);
        blueenemy.setY(blueenemy_y);
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
        blueenemy_x = blueenemy_x - 10;
        blueenemy.setX(blueenemy_x);
        blueenemy.setY(blueenemy_y);
        if(blueenemy_x == 0) {
            blueenemy_x = 715;
            Random r = new Random();
            blueenemy_y = r.nextInt(800);
        }
    }
}
*/
