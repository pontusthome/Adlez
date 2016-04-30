package com.mygdx.game.model.handler;

import com.mygdx.game.model.Collidable;
import com.mygdx.game.model.HitBox;
import com.mygdx.game.model.IWorldObject;
import com.mygdx.game.model.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler2{
	
	private List<Collidable> objects;
	
	public CollisionHandler2(){
		objects = new ArrayList<>();
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
		Collidable[] objectsArray = (Collidable[]) objects.toArray();
		
		int index = 0;
		
		for(Collidable object : objectsArray){
			for(int i = index; index < objectsArray.length; index++)
				checkCollision(object, objectsArray[index]);
			index++;
		}
	}
	
	private void checkCollision(Collidable object1, Collidable object2){
		WorldObject worldObject1 = (WorldObject) object1; 
		WorldObject worldObject2 = (WorldObject) object2;
		
		HitBox hitBox1 = ((WorldObject) object1).getHitBox();
		HitBox hitBox2 = ((WorldObject) object1).getHitBox();
		
		if(hitBox1.overlaps(hitBox2)){
			object1.onCollide(object2);
			object2.onCollide(object1);
		}
	}
}
