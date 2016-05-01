package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject implements IChest {

    private int chestSize;
    private int chestSizeCount = 0;
    private Adlez adlez = Adlez.getInstance();
    private List<IItem> slots = new ArrayList<IItem>(chestSize);

    public Chest(float posX, float posY, int width, int height, int chestSize) {
        super(posX, posY, width, height);
        this.chestSize = chestSize;
        slots = new ArrayList<IItem>(chestSize);
    }

    public void addItems(IItem type) {
        if (chestSizeCount < chestSize) {
            slots.add(type);
        }

    }

    public List<IItem> getItems(Chest chest) {
        return slots;
    }

    // Chest should be removed after chest is closed.
    public void removeChest(Chest chest) {
        adlez.removeChestFromWorld(chest);
    }

    @Override
    public void onCollide(Collidable other) {

    }
}
