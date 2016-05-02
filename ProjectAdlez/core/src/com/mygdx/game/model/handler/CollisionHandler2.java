package com.mygdx.game.model.handler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.controller.CombatHandler;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.view.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler2{
	
	private List<IWorldObject> worldObjects;
	private WorldObject mainWorldObject;
	private WorldObject otherWorldObject;
	private IPlayer player;
	public static Rectangle otherHitbox = new Rectangle();
	public static Rectangle mainHitbox = new Rectangle();
	private boolean movePlayerNorth = true;
	private boolean movePlayerSouth = true;
	private boolean movePlayerEast = true;
	private boolean movePlayerWest = true;
	
	public CollisionHandler2(){
		player = Adlez.getInstance().getPlayer();
		worldObjects = Adlez.getInstance().getWorldObjects();
	}
	
	public void updateWorld(){
		for(IWorldObject mainObject : worldObjects){
			for(IWorldObject otherObject : worldObjects){
				if(mainObject != otherObject && mainObject != player){
					checkCollision((Collidable) mainObject, (Collidable) otherObject);
				}
			}
		}
	}
	
	public void updatePlayer(){
		
		movePlayerNorth = true;
		movePlayerSouth = true;
		movePlayerEast = true;
		movePlayerWest = true;
		
		for(IWorldObject otherObject : worldObjects){
			if(player != otherObject){
				checkCollision((Collidable) player, (Collidable) otherObject);
			}
		}
		
		Character character = (Character) player;
		
		if(movePlayerNorth && character.isMovingNorth()){
			player.moveNorth();
		}else if(movePlayerSouth && character.isMovingSouth()){
			player.moveSouth();
		}else if(movePlayerEast && character.isMovingEast()){
			player.moveEast();
		}else if(movePlayerWest && character.isMovingWest()){
			player.moveWest();
		}
	}
	
	private void checkCollision(Collidable mainObject, Collidable otherObject){
		mainWorldObject = (WorldObject) mainObject; 
		otherWorldObject = (WorldObject) otherObject;
				
		collide(mainWorldObject, otherWorldObject);
	}
	
//	public boolean collide(IWorldObject primaryObject, IWorldObject other) {
//		float width = primaryObject.getWidth();
//		float height = primaryObject.getHeight();
//		float otherWidth = other.getWidth();
//		float otherHeight = other.getHeight();
//		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
//			return false;
//		}
//		float x = primaryObject.getPosX();
//		float y = primaryObject.getPosY();
//		float otherX = other.getPosX();
//		float otherY = other.getPosY();
//		otherWidth += otherX;
//		otherHeight += otherY;
//		width += x;
//		height += y;
//		//      overflow || intersect
//		return ((otherWidth < otherX || otherWidth > x) &&
//				(otherHeight < otherY || otherHeight > y) &&
//				(width < x || width > otherX) &&
//				(height < y || height > otherY));
//	}
	
	private boolean collide(IWorldObject main, IWorldObject other) {
		float width = main.getWidth();
		float height = main.getHeight();
		float otherWidth = other.getWidth();
		float otherHeight = other.getHeight();
		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
			return false;
		}
		float x = main.getPosX();
		float y = main.getPosY();
		float otherX = other.getPosX();
		float otherY = other.getPosY();
		otherWidth += otherX;
		otherHeight += otherY;
		width += x;
		height += y;
		
		if(main instanceof Player){
			Character character = (Character) main;
						
			Rectangle otherHitbox = new Rectangle((int) other.getPosX(), (int) other.getPosY(),
					(int) other.getWidth(), (int) other.getHeight());
						
			if(character.isMovingNorth()){
				mainHitbox = new Rectangle((int) character.getPosX(), (int) character.getPosY() + (int) character.getSpeed(),
						(int) character.getWidth(), (int) character.getHeight());
				Rectangle temp = mainHitbox.intersection(otherHitbox);
				if(temp.getWidth() > 0 && temp.getHeight() > 0){
					movePlayerNorth = false;
				}
			}
			
			if(character.isMovingSouth()){
				mainHitbox = new Rectangle((int) character.getPosX(), (int) character.getPosY() - (int) character.getSpeed(),
						(int) character.getWidth(), (int) character.getHeight());
				Rectangle temp = mainHitbox.intersection(otherHitbox);
				if(temp.getWidth() > 0 && temp.getHeight() > 0){
					movePlayerSouth = false;
				}
			}
			
			if(character.isMovingEast()){
				mainHitbox = new Rectangle((int) character.getPosX() + (int) character.getSpeed(), (int) character.getPosY(),
						(int) character.getWidth(), (int) character.getHeight());
				Rectangle temp = mainHitbox.intersection(otherHitbox);
				if(temp.getWidth() > 0 && temp.getHeight() > 0){
					movePlayerEast = false;
				}
			}
			
			if(character.isMovingWest()){
				mainHitbox = new Rectangle((int) character.getPosX() - (int) character.getSpeed(), (int) character.getPosY(),
						(int) character.getWidth(), (int) character.getHeight());
				Rectangle temp = mainHitbox.intersection(otherHitbox);
				if(temp.getWidth() > 0 && temp.getHeight() > 0){
					movePlayerWest = false;
				}
			}
		}
		
		//      overflow || intersect
		return ((otherWidth < otherX || otherWidth > x) &&
				(otherHeight < otherY || otherHeight > y) &&
				(width < x || width > otherX) &&
				(height < y || height > otherY));
		
		
	}
	
	private void checkCollisionDirection(IWorldObject main, IWorldObject other){
		
	}
}
