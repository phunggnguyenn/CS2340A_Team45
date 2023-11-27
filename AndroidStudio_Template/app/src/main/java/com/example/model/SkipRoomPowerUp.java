package com.example.model;
import android.content.Context;
import android.widget.ImageView;
import com.example.demo_2340.R;
public class SkipRoomPowerUp extends PowerUp {
    public SkipRoomPowerUp(Context context, int x, int y) {
        super(new ImageView(context), x, y);
        getView().setImageResource(R.drawable.key);
        getView().setX(x);
        getView().setY(y);
    }
}
