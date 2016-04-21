package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject {

    private int chestSize;
    List<Item> slots = new ArrayList<Item>(chestSize);

    public void Chest(int chestSize) {
        this.chestSize = chestSize;
    }

    public void addItems(Item type) {
        slots.add(type);
    }
}
