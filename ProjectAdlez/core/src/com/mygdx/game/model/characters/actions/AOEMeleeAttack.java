package com.mygdx.game.model.characters.actions;

/**
 * Created by Michel on 18.5.2016.
 */

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.ICharacter;

/**
 * 
 */
public class AOEMeleeAttack extends Attack{
	
	public AOEMeleeAttack(ICharacter character){
		super(character);
	}
	
	@Override
	public void setInitLocation(ICharacter character){
		setPos(character.getPosX() - character.getWidth(), character.getPosY() - character.getHeight());
		setWidth(character.getWidth() * 3);
		setHeight(character.getHeight() * 3);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	@Override
	public void setDamage(ICharacter character){
		setDamage(character.getAttackDamage());
	}
}
