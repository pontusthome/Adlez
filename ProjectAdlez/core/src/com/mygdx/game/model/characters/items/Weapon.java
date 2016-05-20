package com.mygdx.game.model.characters.items;

import java.io.Serializable;

/**
 * Created by martinso on 20/04/16.
 */
public class Weapon implements IItem, Serializable {

    private String type;
    private String name;
    private int damage;
    private int goldValue;

    public Weapon(String name, String type, int damage, int goldValue) {
        this.type = type;
        this.name = name;
        this.damage = damage;
        this.goldValue = goldValue;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getStats() {
        return damage;
    }

    @Override
    public void setStats(int damage) {
        this.damage = damage;
    }

    @Override
    public int getGoldValue() {
        return goldValue;
    }

    @Override
    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }
}
