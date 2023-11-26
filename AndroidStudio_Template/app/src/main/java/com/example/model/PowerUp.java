package com.example.model;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
public abstract class PowerUp extends AppCompatActivity {
    private ImageView imageView;
    private boolean collected = false;
    private int x;
    private int y;
    public PowerUp(ImageView imageView, int x, int y) {
        this.imageView = imageView;
        this.x = x;
        this.y = y;
    }
    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
    public ImageView getView() {
        return imageView;
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
