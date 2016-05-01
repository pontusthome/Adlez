package com.mygdx.game.model;

public abstract class WorldObject implements IWorldObject, Collidable {

    private float posX;
    private float posY;
    private float oldPosX;
    private float oldPosY;
    private int width;
    private int height;
    private HitBox hitBox;
    
    public WorldObject(){
        
    }
    
    
    public WorldObject(float posX, float posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.oldPosX = posX;
        this.oldPosY = posY;
        this.width = width;
        this.height = height;
        hitBox = new HitBox(posX, posY, width, height);
    }
    
    @Override
    public boolean collide(IWorldObject other) {
        float width = this.getWidth();
        float height = this.getHeight();
        float otherWidth = other.getWidth();
        float otherHeight = other.getHeight();
        if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
            return false;
        }
        float x = this.getPosX();
        float y = this.getPosY();
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

    public float getPosX() {
        return posX;
    }

    public void setPosX(float x) {
        this.posX = x;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float y) {
        this.posY = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public HitBox getHitBox(){
        return hitBox;
    }
    
    public void setHitBox(HitBox hitBox){
        this.hitBox = hitBox;
    }
    
    public float getOldPosX(){
        return oldPosX;
    }
    
    public void setOldPosX(float oldPosX){
        this.oldPosX = oldPosX;
    }
    
    public float getOldPosY(){
        return oldPosY;
    }
    
    public void setOldPosY(float oldPosY){
        this.oldPosY = oldPosY;
    }
    
    public void setToOldPos(){
        setPosX(oldPosX);
        setPosY(oldPosY);
    }
}
