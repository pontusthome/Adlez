package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 8.5.2016.
 */
public class Interaction extends Action implements IInteraction{
	
	ICharacter interactor;
	
	public Interaction(){
		super();
	}
	
	public Interaction(ICharacter interactor){
		super(interactor);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	@Override
	public void setInitLocation(ICharacter character){
		setWidth(character.getWidth());
		setHeight(character.getHeight());
		
		switch(character.getDirection()){
			case Direction.NORTH:
				setPos(character.getPosX(), character.getPosY() + character.getHeight());
				break;
			case Direction.SOUTH:
				setPos(character.getPosX(), character.getPosY() - character.getHeight());
				break;
			case Direction.EAST:
				setPos(character.getPosX() + character.getWidth(), character.getPosY());
				break;
			case Direction.WEST:
				setPos(character.getPosX() - character.getWidth(), character.getPosY());
				break;
		}
	}
}
