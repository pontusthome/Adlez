package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public interface ICharacter {
	void move(float x, float y);

	int getAttackDamage();
	void setAttackDamage(int attackDamage);
	int getHealth();
	void setHealth(int health);
	int getMana();
	void setMana(int mana);
	int getGold();
	void setGold(int gold);
	int getLevel();	
	void setLevel(int level);
	String getName();
	void setName(String name);
	String getCharacterType();
	void setCharacterType(String characterType);
	int getMaxHealth();
	void setMaxHealth(int maxHealth);
	int getMaxMana();
	void setMaxMana(int maxMana);
	int getDirection();
	void setDirection(int direction);
	float getSpeed();
	void setSpeed(float speed);
}
