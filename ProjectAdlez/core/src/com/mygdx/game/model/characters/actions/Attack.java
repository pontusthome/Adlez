package com.mygdx.game.model.characters.actions;

import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 5.5.2016.
 */
public abstract class Attack extends Action implements IAttack{
	
	private int damage;
	private int manaUsage;
	
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
	
	@Override
	public int getManaUsage(){
		return manaUsage;
	}
	
	@Override
	public void setManaUsage(int manaUsage){
		this.manaUsage = manaUsage;
	}
}
