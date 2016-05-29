package com.mygdx.game.model.core;

/**
 * Created by Michel on 2016-05-24.
 */
public interface WorldObjectObserver{
	void update(IWorldObject worldObject, String action, IWorldObject other);
}
