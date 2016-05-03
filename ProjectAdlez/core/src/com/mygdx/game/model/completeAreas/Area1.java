package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 03/05/16.
 */
public class Area1 {

    private static Area area;

    private static float playerPosX;
    private static float playerPosY;

    private static Wall wall;
    private static Obstacle obstacle;

    private static List<INPC> enemies;
    private static List<INPC> friendlyNPCs;
    private static List<IWorldObject> stationaryObjects;
    private static List<IWall> walls;
    private static List<IObstacle> obstacles;
    private static List<IChest> chests;
    private static List<AreaConnection> areaConnections;

    public static Area generateArea() {
        wall = new Wall();

        enemies = new ArrayList<INPC>();
        friendlyNPCs = new ArrayList<INPC>();
        stationaryObjects = new ArrayList<IWorldObject>();
        walls = new ArrayList<IWall>();
        obstacles = new ArrayList<IObstacle>();
        chests = new ArrayList<IChest>();
        areaConnections = new ArrayList<AreaConnection>();


        return area;
    }

    /**
     * Non-moving friendly NPC, acting like a shop for the player.
     */
    public void generateFriendlyNPC(int direction, int width, int height, float posX, float posY) {
        NPC friendlyNPC = new NPC(direction, 0, width, height, posX, posY, 200, 10, 0, 0);
        friendlyNPCs.add(friendlyNPC);
    }

    /**
     * Spawning a group of enemies on a position.
     * Max 4.
     * gold is amount of gold that the player will loot.
     */
    public void generateEnemies(int numberOfEnemies, int direction, float speed, int width, int height, float posX, float posY, int maxHealth, int attackDmg, int gold) {
        if (numberOfEnemies <= 2 || numberOfEnemies > 0) {
            for (int i = 1; i <= numberOfEnemies; i++) {
                NPC enemyNPC = new NPC(direction, speed, width, height, posX * i, posY, maxHealth, attackDmg, 10, 0);
                enemies.add(enemyNPC);
            }
        } else if (numberOfEnemies > 2 || numberOfEnemies < 5) {
            for (int i = 1; i <= 2; i++) {
                for (int j = 1; j <= numberOfEnemies; j++) {
                    NPC enemyNPC = new NPC(direction, speed, width, height, posX, posY * i, maxHealth, attackDmg, 10, 0);
                    enemies.add(enemyNPC);
                }
                numberOfEnemies--;
            }
        }
    }

    /**
     * generating obstacles for this area.
     */
    public void generateObstacles(int x, int y) {
        // TODO: obstacles with these coordinates.
        // x2-5 y5
        //x5 y6-18
        //x2-3 y12
        //x3-4 y14
        //x2-3 y16
        //x3-4 y18

    }

}
