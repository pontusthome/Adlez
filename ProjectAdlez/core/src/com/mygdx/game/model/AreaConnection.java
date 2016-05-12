package com.mygdx.game.model;

import java.io.Serializable;

/**
 * Created by Pontus on 2016-04-29.
 */
public class AreaConnection extends WorldObject implements IAreaConnection, Serializable {

    public AreaConnection(float posX, float posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void onCollide(Collidable other) {
    }
}
