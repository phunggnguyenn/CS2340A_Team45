
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class WhiteEnemy extends Enemy {
    private ImageView whiteenemy;
    private int x;
    private int y;

    public WhiteEnemy(Context context, int x, int y, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy, x, y);
        getView().setImageResource(R.drawable.whiteenemy);
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