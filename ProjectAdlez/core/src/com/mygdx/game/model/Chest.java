package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject {

    private int chestSize;
    List<IItem> slots;

    public void Chest(int chestSize) {
        this.chestSize = chestSize;
        slots = new ArrayList<IItem>(chestSize);
    }

    public void addItems(IItem type) {
        slots.add(type);
    }
}
