package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public class NPC extends Character implements INPC{
	
	private boolean isEnemy;
	private boolean isAlive = true;
	
	public NPC(int direction, float speed, int width, 
			   int height, float posX, float posY, 
			   int maxHealth, int attackDamage, 
			   int gold, int mana) {
		
		super(direction, speed, width, height, 
				posX, posY, maxHealth, 
				attackDamage, gold, mana);
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAliveStatus(boolean bool){
		isAlive = bool;
	}
	
	@Override
	public void onCollide(Collidable other){
		
	}
}
