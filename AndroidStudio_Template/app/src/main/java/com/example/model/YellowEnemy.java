/**
package com.example.model;

import android.os.Handler;
import android.widget.ImageView;

import com.example.demo_2340.R;

import java.util.Random;

public class YellowEnemy extends Enemy {
    ImageView yellowenemy;
    int yellowenemy_x = 715;
    int yellowenemy_y = 65;
    Handler h = new Handler();

    @Override
    void generate() {
        //Instantiate ImageViews for enemy
        yellowenemy = (ImageView) findViewById(R.id.imageYellowEnemy);
        //Set the X and Y coordinate of enemy
        yellowenemy.setX(yellowenemy_x);
        yellowenemy.setY(yellowenemy_y);
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
        yellowenemy_x = yellowenemy_x - 10;
        yellowenemy.setX(yellowenemy_x);
        yellowenemy.setY(yellowenemy_y);
        if(yellowenemy_x == 0) {
            yellowenemy_x = 100;
            Random r = new Random();
            yellowenemy_y = r.nextInt(800);
        }
    }
}
 */

