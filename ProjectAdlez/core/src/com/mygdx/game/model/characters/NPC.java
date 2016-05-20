package com.mygdx.game.model.characters;

import com.mygdx.game.model.core.Collidable;

/**
 * Created by Michel on 2016-04-19.
 */
public abstract class NPC extends Character implements INPC {
	
	private boolean isAlive = true;
	
	public NPC(int direction, float speed, int width, 
			   int height, float posX, float posY, 
			   int maxHealth, int attackDamage, 
			   int gold, int mana) {
		
		super(direction, speed, width, height, 
				posX, posY, maxHealth, 
				attackDamage, gold, mana);
	}
	
	@Override
	public void onCollide(Collidable other){
		super.onCollide(other);
	}
}
