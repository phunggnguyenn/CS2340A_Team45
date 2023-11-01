/**
package com.example.model;

import android.os.Handler;
import android.widget.ImageView;

import com.example.demo_2340.R;

import java.util.Random;

public class WhiteEnemy extends Enemy {
    ImageView whiteenemy;
    int whiteenemy_x = 65;
    int whiteenemy_y = 65;
    Handler h = new Handler();

    @Override
    void generate() {
        //Instantiate ImageViews for enemy
        whiteenemy = (ImageView) findViewById(R.id.imageWhiteEnemy);
        //Set the X and Y coordinate of enemy
        whiteenemy.setX(whiteenemy_x);
        whiteenemy.setY(whiteenemy_y);
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
        whiteenemy_x = whiteenemy_x - 10;
        whiteenemy.setX(whiteenemy_x);
        whiteenemy.setY(whiteenemy_y);
        if(whiteenemy_x == 0) {
            whiteenemy_x = 65;
            Random r = new Random();
            whiteenemy_y = r.nextInt(800);
        }
    }
}
 */

