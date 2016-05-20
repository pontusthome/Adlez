package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 5.5.2016.
 */
public class AOEMagicAttack extends Attack{
	
	public AOEMagicAttack(){
		super();
	}
	
	public AOEMagicAttack(ICharacter character){
		super(character);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	/**
	 * Temporarily set to 5 times the character's attack damage.
	 *
	 * Should depend on something like "character.getRangeMagicAttackDamage()".
	 */
	@Override
	public void setDamage(ICharacter character){
		setDamage(character.getAttackDamage() * 5);
	}
	
	@Override
	public void setInitLocation(ICharacter character){
		setPos(character.getPosX() - character.getWidth(), character.getPosY() - character.getHeight());
		setWidth(character.getWidth() * 3);
		setHeight(character.getHeight() * 3);
	}
}
