
package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;

public class GreenEnemy extends Enemy {

<<<<<<< HEAD
    public GreenEnemy(Context context, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy);
=======
    public GreenEnemy(Context context, int x, int y, EnemyMovementStrategy movementStrategy) {
        super(new ImageView(context), movementStrategy, x, y);
>>>>>>> main
        getView().setImageResource(R.drawable.greenenemy);
    }

    public void move(EnemyMovementStrategy movementStrategy) {
        movementStrategy.move(this);
    }


}

