package com.mygdx.game.model;

/**
 * Created by martinso on 20/04/16.
 */
public class Weapon implements Items {

    private String type;
    private String name;
    private int damage;

    public Weapon(String name, String type, int damage) {
        this.type = type;
        this.name = name;
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
