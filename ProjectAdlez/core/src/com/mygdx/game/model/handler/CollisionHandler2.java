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
	private WorldObject worldObject1;
	private WorldObject worldObject2;
	private Collidable[] objectsArray;
	
	public CollisionHandler2(){
		objects = new ArrayList<>();
		firstUpdate = true;
	}
	
	public void add(Collidable object){
		objects.add(object);
	}
	
	public void addAll(List<?> objects){
		
		for(Object object : objects)
			this.objects.add((Collidable) object);
	}
	
	public void remove(Collidable object){
		objects.remove(object);
	}
	
	public void update(){
		/** Array used for better performance while iterating */
		objectsArray = new Collidable[objects.size()];
		for(int i = 0; i < objectsArray.length; i++){
			objectsArray[i] = objects.get(i);
		}
		
		IPlayer player = Adlez.getInstance().getPlayer();
		
		for(Collidable object : objectsArray){
			for(Collidable otherObject : objectsArray){
				if(object != otherObject && object != player){
					checkCollision(object, otherObject);
				}
			}
		}
	}
	
	public void updatePlayer(){
		/** Array used for better performance while iterating */
		Collidable[] objectsArray = new Collidable[objects.size()];
		for(int i = 0; i < objectsArray.length; i++){
			objectsArray[i] = objects.get(i);
		}
		
		IPlayer player = Adlez.getInstance().getPlayer();
		
		for(Collidable otherObject : objectsArray){
			if(Adlez.getInstance().getPlayer() != otherObject)
				checkCollision((Collidable) player, otherObject);
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
