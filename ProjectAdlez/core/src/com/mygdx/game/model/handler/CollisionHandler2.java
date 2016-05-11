package com.mygdx.game.model.handler;

import com.mygdx.game.model.*;

import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler2{
	
	private List<IWorldObject> worldObjects;
	private IPlayer player;
	
	public CollisionHandler2(){
		player = Adlez.getInstance().getPlayer();
		worldObjects = Adlez.getInstance().getWorldObjects();
	}
	
	public void updateWorld(){		
		for(IWorldObject primary : worldObjects){
			for(IWorldObject other : worldObjects){
				if(primary != other && primary != player){
					checkCollision(primary, other);
				}
			}
		}
	}
	
	public void updatePlayer(){
		for(IWorldObject other : worldObjects){
			if(player != other)
				checkCollision(player, other);
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
}
