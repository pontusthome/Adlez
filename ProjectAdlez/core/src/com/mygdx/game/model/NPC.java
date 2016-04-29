package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public class NPC extends Character implements INPC{
	private boolean isEnemy;
	private boolean isAlive = true;

	public NPC(int direction, float speed,
			   int width, int height,
			   float posX, float posY,
			   int maxHealth, int attackDamage, int gold, int mana) {
		setDirection(direction);
		setSpeed(speed);
		setWidth(width);
		setHeight(height);
		setPosX(posX);
		setPosY(posY);
		setMaxHealth(maxHealth);
		setHealth(maxHealth);
		setAttackDamage(attackDamage);
		setGold(gold);
		setMana(mana);
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAliveStatus(boolean bool){
		isAlive = bool;
	}
}
