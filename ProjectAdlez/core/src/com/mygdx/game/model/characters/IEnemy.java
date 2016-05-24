package com.mygdx.game.model.characters;

/**
 * Created by Michel on 1.5.2016.
 */
public interface IEnemy extends INPC{
    void followPlayer(float deltaT);
    void attackPlayer();
    int getType();
}
