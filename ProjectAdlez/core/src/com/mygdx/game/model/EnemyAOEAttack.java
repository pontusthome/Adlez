package com.mygdx.game.model;

/**
 * Created by Michel on 18.5.2016.
 */

/**
 * 
 */
public class EnemyAOEAttack extends Attack{
	
	public EnemyAOEAttack(){
		super();
	}
	
	public EnemyAOEAttack(ICharacter character){
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
