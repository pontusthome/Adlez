package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public class NPC extends Character {
	private boolean isEnemy;
	private boolean isAlive = true;

	public NPC(int direction, float speed,
			   float width, float height,
			   float posX, float posY,
			   int maxHealth, int attackDamage, int gold) {
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
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAliveStatus(boolean bool){
		isAlive = bool;
	}
}
