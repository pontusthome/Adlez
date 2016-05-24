package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface IObstacle extends IWorldObject {
    void setHealth(int health);
    int getHealth();
    boolean isDestroyed();
}
