package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 25/04/16.
 */
public class Wall extends WorldObject implements IWall {
    
    private int health;
    private Wall singleWall;
    
    public Wall() {
        // Temporary size.
        setHeight(32);
        setWidth(32);
        setHealth(1337);
    }

    public Wall createSingleWall(float posX, float posY, int size) {
        singleWall = new Wall();
        singleWall.setHeight(size);
        singleWall.setWidth(size);
        singleWall.setPosX(posX);
        singleWall.setPosY(posY);
        return singleWall;
    }

    public List<Wall> createAreaBounds(int height, int width, int size) {
        List<Wall> walls = new ArrayList<Wall>();
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0) {
                    Wall newWall = new Wall();
                    newWall.setPosX(j * size);
                    newWall.setPosY(0);
                    walls.add(newWall);
                } else if (i == height - 1) {
                    Wall newWall = new Wall();
                    newWall.setPosX(j * size);
                    newWall.setPosY((height - 1) * size);
                    walls.add(newWall);
                } else {
                    Wall newWall1 = new Wall();
                    Wall newWall2 = new Wall();
                    newWall1.setPosX(0);
                    newWall1.setPosY(i * size);
                    newWall2.setPosX((width - 1) * size);
                    newWall2.setPosY(i * size);
                    walls.add(newWall1);
                    walls.add(newWall2);
                }
            }
        }
        return walls;
    }
    
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof IAttack){
            IAttack attack = (IAttack) other;
            setHealth(getHealth() - attack.getDamage());
        }
    }
    
    @Override
    public void setHealth(int health){
        this.health = health;
    }
    
    @Override
    public int getHealth(){
        return health;
    }
    
    @Override
    public boolean isDestroyed(){
        return getHealth() <= 0;
    }
}
