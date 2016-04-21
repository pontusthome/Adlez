package com.mygdx.game.model;

public abstract class WorldObject {

    private float posX;
    private float posY;
    private float width;
    private float height;

    public boolean collide(WorldObject other) {
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
