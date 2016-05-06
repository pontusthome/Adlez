package com.mygdx.game.model;

/**
 * Created by Michel on 4.5.2016.
 */
public class MeleeAttack extends Attack{
	
	public MeleeAttack(){
		super();
	}
	
	public MeleeAttack(ICharacter character){
		super(character);
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
	
	/**
	 * Temporarily set to the character's attack damage.
	 *
	 * Should depend on something like "character.getWeaponEquipped.getDamage()".
	 */
	@Override
	public void setDamage(ICharacter character){
		setDamage(character.getAttackDamage());
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
