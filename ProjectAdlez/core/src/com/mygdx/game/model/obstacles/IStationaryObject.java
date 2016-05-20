package com.mygdx.game.model.obstacles;

/**
 * Created by Michel on 6.5.2016.
 */
public interface IStationaryObject{
	void setHealth(int health);
	int getHealth();
	boolean isDestroyed();
}
