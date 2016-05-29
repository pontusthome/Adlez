package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 8.5.2016.
 */
public class Interaction extends Action implements IInteraction{
	
	public Interaction(){
		super();
	}
	
	public Interaction(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
					   int characterDirection, boolean byPlayer){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	@Override
	public void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
								int characterDirection){
		setWidth(characterWidth);
		setHeight(characterHeight);
		
		switch(characterDirection){
			case Direction.NORTH:
				setPos(characterXPos, characterYPos + characterHeight);
				break;
			case Direction.SOUTH:
				setPos(characterXPos, characterYPos - characterHeight);
				break;
			case Direction.EAST:
				setPos(characterXPos + characterWidth, characterYPos);
				break;
			case Direction.WEST:
				setPos(characterXPos - characterWidth, characterYPos);
				break;
		}
	}
}
