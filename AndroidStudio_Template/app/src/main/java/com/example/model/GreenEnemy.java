
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class GreenEnemy extends Enemy {

    public GreenEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
        getView().setImageResource(R.drawable.greenenemy);
    }

    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}

