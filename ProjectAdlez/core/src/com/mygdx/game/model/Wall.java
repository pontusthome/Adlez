package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 25/04/16.
 */
public class Wall extends WorldObject {

    private static List<Wall> walls = new ArrayList<>();

    public Wall() {
        // Temporary size.
        setHeight(64/2);
        setWidth(64/2);
    }

    // Temporary parameter: size
    // Game should have same size for all objects.
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


}
