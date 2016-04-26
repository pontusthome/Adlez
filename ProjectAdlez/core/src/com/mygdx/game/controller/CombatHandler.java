package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 26.4.2016.
 */
public class CombatHandler{
	
	private static boolean isEnemyKilled = false;
	
	/** Set to static for debugging purposes in-game */
	public static Rectangle playerWeaponHitbox = new Rectangle(0,0,0,0);
	
	public static void handleMeleeAttack(){
		Adlez adlez = Adlez.getInstance();
		Player player = adlez.getPlayer();
		List<WorldObject> worldObjects = adlez.getWorldObjects();
		
		/** Temporary sound for attack */
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("Gun_Shot.mp3"));
		sound.play(0.01f);
		
		/** Hitboxes for where a player's weapon lands as well as for body of the enemy */
		playerWeaponHitbox = new Rectangle();
		Rectangle enemyHitbox = new Rectangle();
		
		playerWeaponHitbox.setSize(player.getWidth(), player.getHeight());
		
		/** Set player weapon hitbox location depending on where player is facing */
		switch(player.getDirection()){
			case Direction.NORTH:
				playerWeaponHitbox.setPosition(player.getPosX(), player.getPosY() + player.getHeight());
				break;
			case Direction.SOUTH:
				playerWeaponHitbox.setPosition(player.getPosX(), player.getPosY() - player.getHeight());
				break;
			case Direction.EAST:
				playerWeaponHitbox.setPosition(player.getPosX() + player.getWidth(), player.getPosY());
				break;
			case Direction.WEST:
				playerWeaponHitbox.setPosition(player.getPosX() - player.getWidth(), player.getPosY());
				break;
		}
		
		/** Since you can't remove something from a list while iterating through it, this temporary list is used to
		 * keep track of which enemies to remove from the world.
		 */
		List<WorldObject> worldObjectsToRemove = new ArrayList<>();
		
		/** Iterate through all enemies and check if each and everyone of them are inside the weapon hitbox of the 
		 * player. If so, enemy is to be killed and removed from the game.
		 * To be modified later so that consideration is taken to enemy health, player damage etc.*/
		for(WorldObject object : worldObjects){
			if(object instanceof NPC){
				enemyHitbox.set(object.getPosX(), object.getPosY(), object.getWidth(), object.getHeight());
				if (playerWeaponHitbox.overlaps(enemyHitbox)) {
					worldObjectsToRemove.add(object);
					isEnemyKilled = true;
				}
			}
		}
		
		/** Remove enemies that got killed from the world */
		for(WorldObject object : worldObjectsToRemove){
			NPC enemy = (NPC) object;
			enemy.setAliveStatus(false);
			adlez.removeEnemyFromWorld(enemy);
		}
	}
	
	public static boolean isEnemyKilled(){
		return isEnemyKilled;
	}
	
	public static void clearIsEnemyKilled(){
		isEnemyKilled = false;
	}
}
