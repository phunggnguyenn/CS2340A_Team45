package com.example.model;

import java.io.Serializable;

public class Weapon  implements Serializable {
    private int damage;
    private int weaponResourceId;
    private int x; // X-coordinate of the weapon
    private int y; // Y-coordinate of the weapon

    public Weapon(int weaponResourceId, int damage) {
        this.weaponResourceId = weaponResourceId;
        this.damage = damage;
    }


    public int getWeaponResourceId() {
        return weaponResourceId;
    }

    public int getDamage() {
        return damage;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}