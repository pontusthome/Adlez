package com.mygdx.game.model.actions;

/**
 * Created by Michel on 18.5.2016.
 */

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.ICharacter;

/**
 * 
 */
public class AOEMeleeAttack extends Attack{
	
	public AOEMeleeAttack(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
						  int characterDirection , boolean byPlayer, int damage){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer, damage);
	}
	
	@Override
	public void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
								int characterDirection){
		// For now, set size of attack depending on the attacker's size
		setPos(characterXPos - characterWidth, characterYPos - characterHeight);
		setWidth(characterWidth * 3);
		setHeight(characterHeight * 3);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
}
