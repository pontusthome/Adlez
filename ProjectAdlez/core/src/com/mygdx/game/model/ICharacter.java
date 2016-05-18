package com.mygdx.game.model;

import java.util.List;

/**
 * Created by Michel on 2016-04-19.
 */
public interface ICharacter extends IWorldObject{
	void moveNorth(float deltaT);
	void moveSouth(float deltaT);
	void moveWest(float deltaT);
	void moveEast(float deltaT);
	void move(float deltaT);

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
	void setVx(float vX);
	void setVy(float vY);
	void undoCharacterMove(Collidable other);
	boolean isMovingNorth();
	boolean isMovingSouth();
	boolean isMovingEast();
	boolean isMovingWest();
	boolean isAlive();
}
