package com.mygdx.game.model;

public abstract class WorldObject {

    private float posX;
    private float posY;
    private float oldXpos;
    private float oldYpos;
    private int width;
    private int height;

    public void onCollide(WorldObject other) {}

    public float getPosX() {
        return posX;
    }

    public void setPosX(float x) {
        this.oldXpos = posX;
        this.posX = x;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float y) {
        this.oldYpos = posY;
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

    public float getOldXpos() {
        return oldXpos;
    }

    public float getOldYpos() {
        return oldYpos;
    }
}
