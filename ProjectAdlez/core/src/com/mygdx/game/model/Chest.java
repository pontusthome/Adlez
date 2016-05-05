package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject implements IChest {

    private int chestSize;
    private int chestSizeCount = 0;
    private List<IItem> slots = new ArrayList<IItem>(chestSize);
    
    private int health;

    public Chest(float posX, float posY, int width, int height, int chestSize, int health) {
        super(posX, posY, width, height);
        this.chestSize = chestSize;
        setHealth(health);
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }

    public void addItems(IItem type) {
        if (chestSizeCount < chestSize) {
            slots.add(type);
        }
    }

    public List<IItem> getItems(Chest chest) {
        return slots;
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof IAttack){
            IAttack attack = (IAttack) other;
            setHealth(getHealth() - attack.getDamage());
        }
    }
    
    @Override
    public boolean isDestroyed(){
        return getHealth() <= 0;
    }
}
