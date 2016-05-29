package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 8.5.2016.
 */
public interface IAction extends IWorldObject {
	DebugHitbox getDebugHitbox();
	boolean isFinished();
	void setFinished();
	void setInitLocation(ICharacter character);
	ICharacter getCharacter();
	boolean byPlayer();
}
