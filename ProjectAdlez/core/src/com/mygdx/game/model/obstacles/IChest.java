package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.items.IItem;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.exceptions.InventoryFullException;

import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */

public interface IChest extends IWorldObject {
    void addItem(IItem type) throws InventoryFullException;
    List<IItem> getItems();
    boolean isEmpty();
    int getSize();
    boolean isFull();
    void removeItem(IItem item);
    boolean isOpened();
    void setIsOpened(boolean isOpened);
    List<IItem> getSlots();
}
