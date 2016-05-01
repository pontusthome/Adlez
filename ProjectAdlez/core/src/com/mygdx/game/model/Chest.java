package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject implements IChest {

    private Adlez adlez = Adlez.getInstance();
    private int chestSize;
    private List<IItem> slots;

    public Chest(int chestSize) {
        this.chestSize = chestSize;
        slots = new ArrayList<IItem>(chestSize);
    }

    public void addItems(IItem type) {
        slots.add(type);
    }

    public List<IItem> getItems(Chest chest) {
        return slots;
    }

    // Chest should be removed after chest is closed.
    public void removeChest(Chest chest) {
        adlez.removeChestFromWorld(chest);
    }
    
    @Override
    public void onCollide(Collidable other){
        
    }
}
