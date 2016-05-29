package com.mygdx.game.model.items;

/**
 * Created by martinso on 20/04/16.
 */
public interface IItem {
    String getType();
    void setType(String type);
    String getName();
    void setName(String name);
    int getStats();
    void setStats(int stats);
    int getGoldValue();
    void setGoldValue(int goldValue);
}
