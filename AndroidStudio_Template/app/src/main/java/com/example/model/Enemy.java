package com.example.model;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import com.example.demo_2340.R;

public abstract class Enemy extends AppCompatActivity {
    private ImageView imageView;
    private EnemyMovementStrategy movementStrategy;
    private int x;
    private int y;

    public Enemy(ImageView imageView, EnemyMovementStrategy movementStrategy, int x, int y) {
        this.imageView = imageView;
        this.movementStrategy = movementStrategy;
        this.x = x;
        this.y = y;
    }

    public ImageView getView() {
        return imageView;
    }

    public void move() {
        movementStrategy.move(this);
    }


    void generate() {

    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}