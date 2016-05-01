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
//		if(temp < 100){
//			temp++;
//			return;
//		}
		
		/** Array used for better performance while iterating */
		Collidable[] objectsArray = new Collidable[objects.size()];
		for(int i = 0; i < objectsArray.length; i++){
			objectsArray[i] = objects.get(i);
		}
		
		int index = 0;
		
//		for(Collidable object : objectsArray){
//			for(int i = index; index < objectsArray.length; index++)
//				checkCollision(object, objectsArray[index]);
//			index++;
//		}
		
		for(Collidable object : objectsArray){
			for(Collidable otherObject : objectsArray){
				if(otherObject != object && object != Adlez.getInstance().getPlayer()){
					checkCollision(object, otherObject);
				}
			}
			index++;
		}
	}
	
	public void updatePlayer(){
//		if(temp < 100){
//			temp++;
//			return;
//		}
		
		/** Array used for better performance while iterating */
		Collidable[] objectsArray = new Collidable[objects.size()];
		for(int i = 0; i < objectsArray.length; i++){
			objectsArray[i] = objects.get(i);
		}
		
		for(Collidable otherObject : objectsArray){
			checkCollision((Collidable) Adlez.getInstance().getPlayer(), otherObject);
		}
	}
	
	private void checkCollision(Collidable object1, Collidable object2){
		WorldObject worldObject1 = (WorldObject) object1; 
		WorldObject worldObject2 = (WorldObject) object2;
		
		if(collide2(worldObject1, worldObject2)){
			object1.onCollide(object2);
		}
		
		
//		HitBox hitBox1 = ((WorldObject) object1).getHitBox();
//		HitBox hitBox2 = ((WorldObject) object1).getHitBox();
//		
//		if(hitBox1.overlaps(hitBox2)){
//			object1.onCollide(object2);
//			object2.onCollide(object1);
//		}
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
