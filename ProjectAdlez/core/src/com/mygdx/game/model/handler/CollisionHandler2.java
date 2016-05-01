package com.mygdx.game.model.handler;

import com.mygdx.game.model.*;

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
		for(IWorldObject otherObject : worldObjects){
			if(player != otherObject)
				checkCollision((Collidable) player, (Collidable) otherObject);
		}
	}
	
	private void checkCollision(Collidable primaryObject, Collidable otherObject){
		mainWorldObject = (WorldObject) primaryObject; 
		otherWorldObject = (WorldObject) otherObject;
				
		if(collide(mainWorldObject, otherWorldObject)){
			mainWorldObject.onCollide(otherWorldObject);
		}
	}
	
	public boolean collide(IWorldObject primaryObject, IWorldObject other) {
		float width = primaryObject.getWidth();
		float height = primaryObject.getHeight();
		float otherWidth = other.getWidth();
		float otherHeight = other.getHeight();
		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
			return false;
		}
		float x = primaryObject.getPosX();
		float y = primaryObject.getPosY();
		float otherX = other.getPosX();
		float otherY = other.getPosY();
		otherWidth += otherX;
		otherHeight += otherY;
		width += x;
		height += y;
		//      overflow || intersect
		return ((otherWidth < otherX || otherWidth > x) &&
				(otherHeight < otherY || otherHeight > y) &&
				(width < x || width > otherX) &&
				(height < y || height > otherY));
	}
}
