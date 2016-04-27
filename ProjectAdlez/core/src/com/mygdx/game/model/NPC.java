package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public class NPC extends Character {
	private boolean isEnemy;

	public NPC(int direction, float speed,
			   int width, int height,
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

}
