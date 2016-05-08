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
    
    @Override
    public int getHealth() {
        return health;
    }
    
    @Override
    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public void addItems(IItem type) {
        if (chestSizeCount < chestSize) {
            slots.add(type);
        }
    }
    
    @Override
    public List<IItem> getItems(Chest chest) {
        return slots;
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof IAttack){
            IAttack attack = (IAttack) other;
            setHealth(getHealth() - attack.getDamage());
        }
        if(other instanceof IInteraction){
            IInteraction interaction = (IInteraction) other;
            ICharacter character = interaction.getCharacter();
            character.getInventory().addAll(slots);
            setHealth(0);
        }
    }
    
    @Override
    public boolean isDestroyed(){
        return getHealth() <= 0;
    }
}
