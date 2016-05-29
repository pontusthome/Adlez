package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.items.IItem;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.exceptions.InventoryFullException;

import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */

public interface IChest extends IWorldObject {
    public void addItem(IItem type) throws InventoryFullException;

    public List<IItem> getItems();

    public boolean isEmpty();

    public int getSize();

    public boolean isFull();

    public void removeItem(IItem item);

    public boolean isOpened();

    public void setIsOpened(boolean isOpened);
}
