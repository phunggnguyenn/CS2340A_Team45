
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class WhiteEnemy extends Enemy {
    private ImageView whiteenemy;
    private int x;
    private int y;

    public WhiteEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
        getView().setImageResource(R.drawable.whiteenemy);
    }
    @Override
    void generate() {

    }
    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }

}

