package com.mygdx.game.model.characters.items;

/**
 * Created by martinso on 20/04/16.
 */
public interface IItem {

    public String getType();

    public void setType(String type);

    public String getName();

    public void setName(String name);

    public int getStats();

    public void setStats(int stats);

    public int getGoldValue();

    public void setGoldValue(int goldValue);
}
