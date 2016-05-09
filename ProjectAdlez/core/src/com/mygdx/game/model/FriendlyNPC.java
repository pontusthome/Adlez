package com.mygdx.game.model;

import java.io.Serializable;

/**
 * Created by Michel on 1.5.2016.
 */
public class FriendlyNPC extends NPC implements IFriendlyNPC, Serializable {
	
	public FriendlyNPC(int direction, float speed, int width,
				 int height, float posX, float posY,
				 int maxHealth, int attackDamage,
				 int gold, int mana){
		
		super(direction, speed, width, height,
				posX, posY, maxHealth, attackDamage,
				gold, mana);
	}
	
	@Override
	public void onCollide(Collidable other){
		super.onCollide(other);
	}
}
