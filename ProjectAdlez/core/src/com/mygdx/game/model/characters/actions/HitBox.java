package com.mygdx.game.model.characters.actions;

//TODO: Remove HitBox since it's only for debugging
/**
 * Created by Michel on 30.4.2016.
 */
public class HitBox{
	private float x;
	private float y;
	private float width;
	private float height;
	
	public HitBox(){
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public HitBox(float x, float y, float width, float height){
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
