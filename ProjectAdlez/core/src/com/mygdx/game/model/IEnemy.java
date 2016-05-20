package com.mygdx.game.model;

/**
 * Created by Michel on 1.5.2016.
 */
public interface IEnemy extends INPC{
    public void followPlayer(float deltaT);
    public int getType();
}
