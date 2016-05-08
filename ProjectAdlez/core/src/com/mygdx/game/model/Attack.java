package com.mygdx.game.model;

/**
 * Created by Michel on 5.5.2016.
 */
public abstract class Attack extends Action implements IAttack{
	
	private int damage;
	
	public Attack(){
		super();
	}
	
	/** Set position of attack in front of character. For now also set size the same as character's size */
	public Attack(ICharacter character){
		super(character);
		setDamage(character);
	}
	
	@Override
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	@Override
	public int getDamage(){
		return damage;
	}
	
	
}
