
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;


public class BlueEnemy extends Enemy {
    private ImageView blueenemy;
    private int x;
    private int y;

    public BlueEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
        getView().setImageResource(R.drawable.blueenemy);

    }

    @Override
    void generate() {

    }
    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}

