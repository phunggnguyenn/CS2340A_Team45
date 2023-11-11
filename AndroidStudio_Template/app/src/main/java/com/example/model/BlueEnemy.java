
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;


public class BlueEnemy extends Enemy {


    public BlueEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
    //int blueenemy_x = 715;
    // int blueenemy_y = 65;
    private ImageView blueenemy;

    public BlueEnemy(Context context, int x, int y, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy, x, y);

        getView().setImageResource(R.drawable.blueenemy);

    }


    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}

