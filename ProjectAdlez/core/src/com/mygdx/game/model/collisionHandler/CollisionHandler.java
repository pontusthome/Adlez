package com.mygdx.game.model.collisionHandler;

import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.core.IWorldObject;

import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler {
	
	private List<IWorldObject> worldObjects;
	private IPlayer player;
	private static CollisionHandler collisionHandler = new CollisionHandler();
	
	public static CollisionHandler getInstance() {
		return collisionHandler;
	}
	
	private CollisionHandler(){
		
	}
	
	public void initiate(IPlayer player, List<IWorldObject> worldObjects){
		this.player = player;
		this.worldObjects = worldObjects;
	}
	
	public void updateWorld(){		
		for(IWorldObject primary : worldObjects){
			for(IWorldObject other : worldObjects){
				if(primary != other){
					checkCollision(primary, other);
				}
			}
		}
	}
	
	private void checkCollision(IWorldObject primary, IWorldObject other){
		if(collide(primary, other)){
			primary.onCollide(other);
		}
	}
	
	public static boolean collide(IWorldObject primary, IWorldObject other) {
		float width = primary.getWidth();
		float height = primary.getHeight();
		float otherWidth = other.getWidth();
		float otherHeight = other.getHeight();
		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
			return false;
		}
		float x = primary.getPosX();
		float y = primary.getPosY();
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
	
	public boolean characterCollided(ICharacter character){
		
		for (IWorldObject otherObject: worldObjects) {
			if (!otherObject.equals(character) && character.collide(otherObject)) {
				return true;
			}
		}
		return false;
	}
}
