package com.mygdx.game.model;

import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface IChest extends IWorldObject, IStationaryObject{
    public void addItems(IItem type);
    public List<IItem> getItems(Chest chest);
    public boolean isEmpty();
}
