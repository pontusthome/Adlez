package com.mygdx.game.model.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 5.5.2016.
 */
public class AOEMagicAttack extends Attack{
	
	public AOEMagicAttack(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
						  int characterDirection , boolean byPlayer, int damage){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer, damage);
		setManaUsage(Attack.AOE_MAGIC_ATTACK_MANA_USAGE);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	@Override
	public void setInitLocation(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
								int characterDirection){
		// For now, set size of attack depending on the attacker's size
		setPos(characterXPos - characterWidth, characterYPos - characterHeight);
		setWidth(characterWidth * 3);
		setHeight(characterHeight * 3);
	}
}
