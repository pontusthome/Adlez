package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.core.GameSound;
import com.mygdx.game.model.core.WorldObject;
import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.Player;

/**
 * Created by Michel on 8.5.2016.
 */
public abstract class Action extends WorldObject implements IAction{
	
	private HitBox hitBox = new HitBox();
	private boolean isFinished;
	private ICharacter character;
	private boolean byPlayer;
	
	public Action(){
		
	}
	
	/** Set position of action in front of character. For now also set size the same as character's size */
	public Action(ICharacter character){
		this.character = character;
		setInitLocation(character);
		hitBox = new HitBox(getPosX(), getPosY(), getWidth(), getHeight());
		
		byPlayer = character instanceof Player;
	}
	
	@Override
	public HitBox getHitBox(){
		return hitBox;
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
	public ICharacter getCharacter(){
		return character;
	}
	
	@Override
	public boolean byPlayer(){
		return byPlayer;
	}
}
