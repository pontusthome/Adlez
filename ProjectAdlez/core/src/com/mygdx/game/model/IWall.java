package com.mygdx.game.model;

import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface IWall extends IWorldObject {
    public List<Wall> createAreaBounds(int height, int width, int size);
}
