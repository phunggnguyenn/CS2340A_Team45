
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;


public class BlueEnemy extends Enemy {
    //int blueenemy_x = 715;
    // int blueenemy_y = 65;
    private ImageView blueenemy;

    public BlueEnemy(Context context, int x, int y, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy, x, y);
        getView().setImageResource(R.drawable.blueenemy);
        getView().setX(x);
        getView().setY(y);
    }

    @Override
    void generate() {

    }
    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}