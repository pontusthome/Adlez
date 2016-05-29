package com.mygdx.game.model.characters.actions;

/**
 * Created by Michel on 30.4.2016.
 * 
 * Class used for debugging of an action's position
 * 
 */
public class DebugHitbox{
	private float x;
	private float y;
	private float width;
	private float height;
	
	public DebugHitbox(){
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public DebugHitbox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
}
