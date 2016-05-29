package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by Michel on 8.5.2016.
 */
public interface IAction extends IWorldObject {
	DebugHitbox getDebugHitbox();
	boolean isFinished();
	void setFinished();
	void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight, 
						 int characterDirection);
	boolean byPlayer();
}
