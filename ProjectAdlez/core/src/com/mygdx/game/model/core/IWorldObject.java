package com.mygdx.game.model.core;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface IWorldObject extends Collidable{
    boolean collide(IWorldObject object);
    float getPosX();
    void setPosX(float x);
    float getPosY();
    void setPosY(float y);
    int getWidth();
    void setWidth(int width);
    int getHeight();
    void setHeight(int height);
    void setPos(float x, float y);
}
