
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class YellowEnemy extends Enemy {
    //int blueenemy_x = 715;
    // int blueenemy_y = 65;
    private ImageView yellowenemy;
    private int x;
    private int y;

    public YellowEnemy(Context context, int x, int y) {
        super(new ImageView(context));
        getView().setImageResource(R.drawable.yellowenemy);
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

