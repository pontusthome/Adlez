package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 5.5.2016.
 */
public class RangeMagicAttack extends Attack{
	
	public RangeMagicAttack(){
		super();
	}
	
	public RangeMagicAttack(ICharacter character){
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
		switch(character.getDirection()){
			case Direction.NORTH:
				setPos(character.getPosX(), character.getPosY() + character.getHeight());
				setWidth(character.getWidth());
				setHeight(character.getHeight() * 3);
				break;
			case Direction.SOUTH:
				setPos(character.getPosX(), character.getPosY() - character.getHeight() * 3);
				setWidth(character.getWidth());
				setHeight(character.getHeight() * 3);
				break;
			case Direction.EAST:
				setPos(character.getPosX() + character.getWidth(), character.getPosY());
				setWidth(character.getWidth() * 3);
				setHeight(character.getHeight());
				break;
			case Direction.WEST:
				setPos(character.getPosX() - character.getWidth() * 3, character.getPosY());
				setWidth(character.getWidth() * 3);
				setHeight(character.getHeight());
				break;
		}
	}
}
