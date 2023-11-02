package com.example.model;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public abstract class Enemy extends AppCompatActivity {
    private ImageView imageView;

    public Enemy(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getView() {
        return imageView;
    }

    void generate(){

    }
}
