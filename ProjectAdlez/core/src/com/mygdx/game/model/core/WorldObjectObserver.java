package com.mygdx.game.model.core;

import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by Michel on 2016-05-24.
 */
public interface WorldObjectObserver{
	void update(IWorldObject worldObject, Object arg);
}
