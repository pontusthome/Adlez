package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.WorldObject;

/**
 * Created by Michel on 8.5.2016.
 */
public abstract class Action extends WorldObject implements IAction{
	
	private DebugHitbox debugHitbox = new DebugHitbox();
	private boolean isFinished;
	private boolean byPlayer;
	
	public Action(){
		
	}
	
	/** Set position of action in front of character. For now also set size the same as character's size */
	public Action(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
				  int characterDirection, boolean byPlayer){
		setInitLocation(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection);
		debugHitbox = new DebugHitbox(getPosX(), getPosY(), getWidth(), getHeight());
		
		this.byPlayer = byPlayer;
	}
	
	public DebugHitbox getDebugHitbox(){
		return debugHitbox;
	}
	
	@Override
	public boolean isFinished(){
		return isFinished;
	}
	
	@Override
	public void setFinished(){
		isFinished = true;
	}
	
	@Override
	public boolean byPlayer(){
		return byPlayer;
	}
}
