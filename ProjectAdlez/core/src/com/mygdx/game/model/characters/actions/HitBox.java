package com.mygdx.game.model.characters.actions;

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
	
	public void set(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public float getX(){
		return x;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setSize(float width, float height){
		this.width = width;
		this.height = height;
	}
	
	public boolean overlaps(HitBox otherBox){
		return x < otherBox.getX() + otherBox.getWidth() && 
				x + width > otherBox.getX() && 
				y < otherBox.getY() + otherBox.getHeight() && 
				y + height > otherBox.getY();
	}
	
	public String toString () {
		return "[" + x + "," + y + "," + width + "," + height + "]";
	}
}
