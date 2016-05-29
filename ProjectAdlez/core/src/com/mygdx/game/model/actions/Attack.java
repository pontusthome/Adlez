package com.mygdx.game.model.actions;

/**
 * Created by Michel on 5.5.2016.
 */
public abstract class Attack extends Action implements IAttack{
	
	private int damage;
	private int manaUsage;
	public static final int RANGE_MAGIC_ATTACK_MANA_USAGE = 15;
	public static final int AOE_MAGIC_ATTACK_MANA_USAGE = 20;
	public static final int RANGE_MAGIC_ATTACK_DAMAGE = 100;
	public static final int AOE_MAGIC_ATTACK_DAMAGE = 100;
	
	public Attack(){
		super();
	}
	
	/** Set position of attack in front of character. For now also set size the same as character's size */
	public Attack(float characterXPos, float characterYPos, int characterWidth, int characterHeight,
				  int characterDirection, boolean byPlayer, int damage){
		super(characterXPos, characterYPos, characterWidth, characterHeight, characterDirection, byPlayer);
		setDamage(damage);
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
