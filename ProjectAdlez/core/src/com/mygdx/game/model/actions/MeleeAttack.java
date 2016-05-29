package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 4.5.2016.
 */
public class MeleeAttack extends Attack{
	
	public MeleeAttack(){
		super();
	}
	
	public MeleeAttack(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
					   int characterDirection , boolean byPlayer, int damage){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer, damage);
	}
		
	@Override
	public void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
								int characterDirection){
		// For now, set size of attack depending on the attacker's size
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
	
	@Override
	public void onCollide(Collidable other){
		
	}
}
