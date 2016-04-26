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
	
	/** Set to public & static for debugging purposes in-game */
	public static Rectangle playerWeaponHitbox = new Rectangle();
	public static Rectangle enemyHitbox = new Rectangle();
	private static Sound sound;
	
	public static void handleMeleeAttack(){
		sound = Gdx.audio.newSound(Gdx.files.internal("punch2.wav"));
		sound.play(0.1f);
		playerWeaponHitbox = createPlayerMeleeHitbox();
		attackEnemies(playerWeaponHitbox, 1);
	}
	
	public static void handleAOEAttack(){
		sound = Gdx.audio.newSound(Gdx.files.internal("fire_attack.wav"));
		sound.play(0.5f);
		playerWeaponHitbox = createPlayerAOEHitbox();
		attackEnemies(playerWeaponHitbox, 5);
	}
	
	public static void handleRangeAttack(){
		sound = Gdx.audio.newSound(Gdx.files.internal("ice_attack.wav"));
		sound.play(0.1f);
		playerWeaponHitbox = createPlayerRangeHitbox();
		attackEnemies(playerWeaponHitbox, 5);
	}
	
	public static boolean isEnemyKilled(){
		return isEnemyKilled;
	}
	
	public static void clearIsEnemyKilled(){
		isEnemyKilled = false;
	}
	
	private static Rectangle createPlayerMeleeHitbox(){
		Player player = Adlez.getInstance().getPlayer();
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
		return playerWeaponHitbox;
	}
	
	private static Rectangle createPlayerAOEHitbox(){
		Player player = Adlez.getInstance().getPlayer();
		playerWeaponHitbox.set(player.getPosX() - player.getWidth(), player.getPosY() - player.getHeight(), 
				player.getWidth() * 3, player.getHeight() * 3);
		return playerWeaponHitbox;
	}
	
	private static Rectangle createPlayerRangeHitbox(){
		Player player = Adlez.getInstance().getPlayer();
		
		/** Set player weapon hitbox location depending on where player is facing */
		switch(player.getDirection()){
			case Direction.NORTH:
				playerWeaponHitbox.set(player.getPosX(), player.getPosY() + player.getHeight(), player.getWidth(),
						player.getHeight() * 3);
				break;
			case Direction.SOUTH:
				playerWeaponHitbox.set(player.getPosX(), player.getPosY() - player.getHeight() * 3, player.getWidth(),
						player.getHeight() * 3);
				break;
			case Direction.EAST:
				playerWeaponHitbox.set(player.getPosX() + player.getWidth(), player.getPosY(), player.getWidth() * 3,
						player.getHeight());
				break;
			case Direction.WEST:
				playerWeaponHitbox.set(player.getPosX() - player.getWidth() * 3, player.getPosY(), player.getWidth() * 3,
						player.getHeight());
				break;
		}
		return playerWeaponHitbox;
	}
	
	private static void attackEnemies(Rectangle hitbox, int attackModifier){
		Adlez adlez = Adlez.getInstance();
		Player player = adlez.getPlayer();
		List<WorldObject> worldObjects = adlez.getWorldObjects();
		
		/** Since you can't remove something from a list while iterating through it, this temporary list is used to
		 * keep track of which enemies to remove from the world.
		 */
		List<WorldObject> worldObjectsToRemove = new ArrayList<>();
		
		for(WorldObject object : worldObjects){
			if(object instanceof NPC){
				enemyHitbox.set(object.getPosX(), object.getPosY(), object.getWidth(), object.getHeight());
				if (playerWeaponHitbox.overlaps(enemyHitbox)) {
					NPC enemy = (NPC) object;
					enemy.setHealth(enemy.getHealth() - player.getAttackDamage() * attackModifier);
					if(enemy.getHealth() <= 0){
						worldObjectsToRemove.add(object);
						isEnemyKilled = true;
					}
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
}
