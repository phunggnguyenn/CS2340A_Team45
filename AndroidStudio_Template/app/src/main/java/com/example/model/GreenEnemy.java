
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class GreenEnemy extends Enemy {
    private ImageView greenenemy;
    private int x;
    private int y;

    public GreenEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
        getView().setImageResource(R.drawable.greenenemy);
    }

    @Override
    void generate() {

    }
    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}

