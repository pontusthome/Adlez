package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 25/04/16.
 */
public class Wall extends WorldObject implements IWall {

    List<Wall> walls = new ArrayList<Wall>();

    public Wall() {
        setHeight(32);
        setWidth(32);
    }

    public Wall(int posX, int posY) {
        setHeight(32);
        setWidth(32);
        setPosX(posX * 32);
        setPosY(posY * 32);
    }

    public List<Wall> createAreaBounds(int height, int width, int size) {
        
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

    public List<Wall> getWalls() {
        return walls;
    }
    
    
    @Override
    public void onCollide(Collidable other){
        
    }
}
