package com.mygdx.game.event;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public class AreaHandler {

    private static AreaHandler areaHandler = new AreaHandler();

    public static AreaHandler getInstance() {
        return areaHandler;
    }

    private Area level1;
    private Area level2;

    // Create the setup for each level
    private AreaHandler() {
        createLevel1();
        createLevel2();
    }

    public Area loadLevel1() {
        return level1;
    }

    public Area loadLevel2() {
        return level2;
    }

    private void createLevel1() {
        float playerXposition = 40f;
        float playerYposition = 40f;
        float xPos;
        float yPos;
        List<INPC> enemies = new ArrayList<INPC>();
        List<INPC>friendlyNPCs = new ArrayList<INPC>();
        List<IWorldObject>stationaryObjects = new ArrayList<IWorldObject>();
        List<IWall>walls = new ArrayList<IWall>();
        List<IObstacle>obstacles = new ArrayList<IObstacle>();
        List<IChest>chests = new ArrayList<IChest>();
        List<AreaConnection>areaConnections = new ArrayList<AreaConnection>();

        Wall wall = new Wall();
        walls.addAll(wall.createAreaBounds(10, 10, 64 / 2));

        Obstacle obst1 = new Obstacle(180, 32, 32, 32, 0);
        obstacles.add(obst1);
        Obstacle obst2 = new Obstacle(180, 64, 32, 32, 0);
        obstacles.add(obst2);
        Obstacle obst3 = new Obstacle(32, 140, 32, 32, 0);
        obstacles.add(obst3);

        Chest ch1 = new Chest(120, 270, 16, 16, 2);
        chests.add(ch1);
        xPos = 120f;
        yPos = 250f;
        enemies.add(regularEnemy(xPos, yPos));
        xPos = 140f;
        yPos = 265f;
        enemies.add(regularEnemy(xPos, yPos));
        xPos = 100f;
        yPos = 265f;
        enemies.add(regularEnemy(xPos, yPos));

        Chest ch2 = new Chest(196+16, 64+16, 16, 16, 2);
        chests.add(ch2);
        xPos = 240f;
        yPos = 80f;
        enemies.add(regularEnemy(xPos, yPos));
        xPos = 260f;
        yPos = 80f;
        enemies.add(regularEnemy(xPos, yPos));

        areaConnections.add(new AreaConnection(260f, 260f));

        level1 = new Area(playerXposition, playerYposition,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests);
    }

    private void createLevel2() {
        float playerXposition = 260;
        float playerYposition = 260;
        float xPos;
        float yPos;
        List<INPC> enemies = new ArrayList<INPC>();
        List<INPC>friendlyNPCs = new ArrayList<INPC>();
        List<IWorldObject>stationaryObjects = new ArrayList<IWorldObject>();
        List<IWall>walls = new ArrayList<IWall>();
        List<IObstacle>obstacles = new ArrayList<IObstacle>();
        List<IChest>chests = new ArrayList<IChest>();
        List<AreaConnection>areaConnections = new ArrayList<AreaConnection>();

        xPos = 40f;
        yPos = 200f;
        enemies.add(regularEnemy(xPos, yPos));
        xPos = 40f;
        yPos = 40f;
        enemies.add(regularEnemy(xPos, yPos));
        xPos = 230f;
        yPos = 40f;
        enemies.add(regularEnemy(xPos, yPos));

        Wall wall = new Wall();
        walls.addAll(wall.createAreaBounds(10, 10, 64 / 2));

        Obstacle obst1 = new Obstacle(32 * 5, 32 * 5, 32, 32, 0);
        Obstacle obst2 = new Obstacle(32 * 7, 32 * 7, 32, 32, 0);
        obstacles.add(obst1);
        obstacles.add(obst2);

        Chest ch1 = new Chest(32 * 4 + 8, 32 * 2 + 8, 16, 16, 2);
        Chest ch2 = new Chest(32 * 7 + 8, 32 * 2 + 8, 16, 16, 2);
        chests.add(ch1);
        chests.add(ch2);

        areaConnections.add(new AreaConnection(40f, 40f));

        level2 = new Area(playerXposition, playerYposition,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests);
    }

    public NPC regularEnemy(float xPos, float yPos) {
        float speed = 1.2f;
        int width = 17;
        int height = 17;
        int health = 100;
        int damage = 5;
        int gold = 10;
        int mana = 100;

        return new NPC(Direction.NORTH, speed,
                width, height,
                xPos, yPos,
                health, damage, gold, mana);
    }
}
