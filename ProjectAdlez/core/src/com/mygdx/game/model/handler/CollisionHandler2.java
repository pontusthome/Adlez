package com.mygdx.game.model.handler;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler2{
	
	private List<Collidable> objects;
	private boolean firstUpdate;
	private int temp = 0;
	private List<IWorldObject> worldObjects;
	private WorldObject worldObject1;
	private WorldObject worldObject2;
	private IPlayer player;
	
	public CollisionHandler2(){
		objects = new ArrayList<>();
		firstUpdate = true;
		player = Adlez.getInstance().getPlayer();
		worldObjects = Adlez.getInstance().getWorldObjects();
	}
	
	public void update(){		
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
	
	private void checkCollision(Collidable object1, Collidable object2){
		worldObject1 = (WorldObject) object1; 
		worldObject2 = (WorldObject) object2;
				
		if(collide2(worldObject1, worldObject2)){
			object1.onCollide(object2);
		}
	}
	
	public boolean collide2(IWorldObject o1, IWorldObject other) {
		float width = o1.getWidth();
		float height = o1.getHeight();
		float otherWidth = other.getWidth();
		float otherHeight = other.getHeight();
		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
			return false;
		}
		float x = o1.getPosX();
		float y = o1.getPosY();
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
