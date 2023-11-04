package com.example.model;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public abstract class Enemy extends AppCompatActivity {
    private ImageView imageView;
    private EnemyMovementStrategy movementStrategy;

    public Enemy(ImageView imageView, EnemyMovementStrategy movementStrategy) {
        this.imageView = imageView;
        this.movementStrategy = movementStrategy;
    }

    public ImageView getView() {
        return imageView;
    }

    public void move() {
        movementStrategy.move(this);
    }


}
