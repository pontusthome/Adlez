package com.mygdx.game.model;

/**
 * Created by Michel on 2016-04-19.
 */
public class NPC extends Character {
	private boolean isEnemy;

	public NPC() {
		setDirection(Direction.NORTH);
		setSpeed(2f);
		setWidth(10);
		setHeight(10);
	}

}
