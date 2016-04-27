package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 25/04/16.
 */
public class Wall extends WorldObject {

    private static List<Wall> walls = new ArrayList<>();

    public Wall() {
        setHeight(64);
        setWidth(64);
    }

    public List<Wall> createAreaBounds(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0) {
                    Wall newWall = new Wall();
                    newWall.setPosX(j * 64);
                    newWall.setPosY(0);
                    walls.add(newWall);
                } else if (i == height - 1) {
                    Wall newWall = new Wall();
                    newWall.setPosX(j * 64);
                    newWall.setPosY((height - 1) * 64);
                    walls.add(newWall);
                } else {
                    Wall newWall1 = new Wall();
                    Wall newWall2 = new Wall();
                    newWall1.setPosX(0);
                    newWall1.setPosY(i * 64);
                    newWall2.setPosX((width - 1) * 64);
                    newWall2.setPosY(i * 64);
                    walls.add(newWall1);
                    walls.add(newWall2);
                }
            }
        }
        return walls;
    }


}
