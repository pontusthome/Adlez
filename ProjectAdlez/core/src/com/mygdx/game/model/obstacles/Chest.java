package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.characters.items.IItem;
import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.WorldObject;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.exceptions.InventoryFullException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 20/04/16.
 */
public class Chest extends WorldObject implements IChest {

    private int chestSize;
    private int chestSizeCount = 0;
    private List<IItem> slots;
    private boolean isOpened = false;

    public Chest(float posX, float posY, int width, int height, int chestSize) {
        super(posX, posY, width, height);
        this.chestSize = chestSize;
        slots = new ArrayList<>(chestSize);
    }

    public Chest(float posX, float posY) {
        super(posX, posY, 16, 16);
        chestSize = 2;
        slots = new ArrayList<>(chestSize);
    }

    @Override
    public void addItem(IItem type) throws InventoryFullException {
        if (chestSizeCount < chestSize) {
            slots.add(type);
            chestSizeCount++;
        } else {
            throw new InventoryFullException("Chest is full");
        }
    }

    @Override
    public int getSize() {
        return chestSize;
    }
    
    @Override
    public List<IItem> getItems() {
        return slots;
    }

    public boolean isFull() {
        return chestSizeCount == chestSize;
    }

    public void removeItem(IItem item) {
        slots.remove(item);
        chestSizeCount--;
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof IInteraction){
            IInteraction interaction = (IInteraction) other;
            if(interaction.getCharacter() instanceof IPlayer){
                IPlayer player = (IPlayer) interaction.getCharacter();
                if(!isOpened()) {
                    for (IItem item : slots) {
                        try {
                            setIsOpened(true);
                            player.lootItem(item);
                        } catch (InventoryFullException e) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
    }
    
    @Override
    public boolean isEmpty(){
        return chestSizeCount <= 0;
    }
}
