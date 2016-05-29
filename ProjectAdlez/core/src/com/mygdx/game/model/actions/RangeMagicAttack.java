package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 5.5.2016.
 */
public class RangeMagicAttack extends Attack{
	
	public RangeMagicAttack(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
							int characterDirection , boolean byPlayer, int damage){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer, damage);
		setManaUsage(Attack.RANGE_MAGIC_ATTACK_MANA_USAGE);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	@Override
	public void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
								int characterDirection){
		// For now, set size and position of attack depending on the attacker's size and position
		switch(characterDirection){
			case Direction.NORTH:
				setPos(characterXPos, characterYPos + characterHeight);
				setWidth(characterWidth);
				setHeight(characterHeight * 3);
				break;
			case Direction.SOUTH:
				setPos(characterXPos, characterYPos - characterHeight *3);
				setWidth(characterWidth);
				setHeight(characterHeight * 3);
				break;
			case Direction.EAST:
				setPos(characterXPos + characterWidth, characterYPos);
				setWidth(characterWidth * 3);
				setHeight(characterHeight);
				break;
			case Direction.WEST:
				setPos(characterXPos - characterWidth * 3, characterYPos);
				setWidth(characterWidth * 3);
				setHeight(characterHeight);
				break;
		}
	}
}
