package com.mygdx.game.event;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public final class AreaHandler {

    private static Area area;

    private static float playerXposition;
    private static float playerYposition;

    private static List<NPC> enemies;
    private static List<NPC> friendlyNPCs;
    private static List<WorldObject> stationaryObjects;
    private static List<Wall> walls;
    private static List<Obstacles> obstacles;

    /**
     * If we want to save the areas as they are when the player leaves them
     * a list of areas might help with that? /PT 24/4
     */
    public static Area testLevel() {
        playerXposition = 64;
        playerYposition = 64;

        enemies = new ArrayList<NPC>();
        friendlyNPCs = new ArrayList<NPC>();
        stationaryObjects = new ArrayList<WorldObject>();
        walls = new ArrayList<Wall>();
        obstacles = new ArrayList<Obstacles>();

        for (int i = 0; i < 10; i++) {
            float speed = random.nextFloat() * (1.5f - 1f) + 1;
            float xPos = random.nextInt(250) + 35;
            float yPos = random.nextInt(250) + 35;

            /** Set NPC's width & height to size of texture for debugging purposes */
            NPC enemy = new NPC(Direction.NORTH, speed,
                                17, 17,
                                xPos, yPos,
                                100, 5, 0, 100);
            enemies.add(enemy);
        }
        Wall wall = new Wall();
        walls.addAll(wall.createAreaBounds(10, 10, 64/2));

        Obstacles obst1 = new Obstacles(32*5,32*5,32,32,0);
        Obstacles obst2 = new Obstacles(32*7,32*7,32,32,0);
        obstacles.add(obst1);
        obstacles.add(obst2);

        area = new Area(playerXposition, playerYposition,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles);

        return area;
    }

}
