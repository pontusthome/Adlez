package com.mygdx.game.model.characters;

import com.mygdx.game.model.characters.actions.*;
import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by Michel on 2016-04-19.
 */
public interface ICharacter extends IWorldObject {
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
	int getMaxHealth();
	void setMaxHealth(int maxHealth);
	int getMaxMana();
	void setMaxMana(int maxMana);
	int getDirection();
	void setDirection(int direction);
	float getSpeed();
	void setSpeed(float speed);
	void setMovingNorth();
	void setMovingSouth();
	void setMovingEast();
	void setMovingWest();
	boolean isAlive();
	void update(float deltaT);
	void setAttackCooldown(float attackCooldown);
	float getAttackCooldown();
	void resetAttackCooldown();
	void handleMoveCollision();
	void meleeAttack();
	void aoeMeleeAttack();
	void aoeMagicAttack();
	void rangeMagicAttack();
	void useMana(IAttack attack);
	void interact();
	void setMoved(boolean moved);
	boolean moved();
	
	
	//TODO: Remove when debugging is over
	IAttack getLatestAttack();
	IInteraction getLatestInteraction();
}
