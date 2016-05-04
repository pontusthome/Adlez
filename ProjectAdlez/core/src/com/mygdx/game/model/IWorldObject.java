package com.mygdx.game.model;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface IWorldObject extends Collidable{
    public boolean collide(IWorldObject object);
    public float getPosX();
    public void setPosX(float x);
    public float getPosY();
    public void setPosY(float y);
    public int getWidth();
    public void setWidth(int width);
    public int getHeight();
    public void setHeight(int height);
}
