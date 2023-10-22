package com.example.model;

import android.widget.ImageView;

import java.util.List;

public interface PlayerMovementStrategy {
    void move(Player player, int keyCode);
    boolean isValidMove(List<ImageView> blackTilesList, int x, int y, Player player);
}


