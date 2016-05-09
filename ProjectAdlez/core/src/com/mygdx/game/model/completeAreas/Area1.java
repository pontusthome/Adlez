package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 03/05/16.
 */
public class Area1 implements ICompleteArea {

    private Area area;

    private float playerPosX;
    private float playerPosY;

    private Wall wall;
    private Obstacle obstacle;

    private List<IEnemy> enemies;
    private List<IFriendlyNPC> friendlyNPCs;
    private List<IWorldObject> stationaryObjects;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;
    private List<IAreaConnection> areaConnections;

    public Area loadArea() {
        return area;
    }

    public Area1() {
        wall = new Wall();

        enemies = new ArrayList<IEnemy>();
        friendlyNPCs = new ArrayList<IFriendlyNPC>();
        stationaryObjects = new ArrayList<IWorldObject>();
        walls = new ArrayList<IWall>();
        obstacles = new ArrayList<IObstacle>();
        chests = new ArrayList<IChest>();
        areaConnections = new ArrayList<IAreaConnection>();

        // Spawning left bottom corner.
        playerPosX = 32;
        playerPosY = 32;


        walls.addAll(wall.createAreaBounds(10, 20, 32));

        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 1, 32 * 5));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 2, 32 * 5));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 3, 32 * 5));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 4, 32 * 5));

        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 8, 32 * 8));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 9, 32 * 8));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 8, 32 * 2));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 9, 32 * 2));

        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 14, 32 * 2));
        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 14, 32 * 7));

        Chest ch1 = new Chest(32 * 18 + 8, 32 * 7 + 8, 16, 16, 2, 200);
        chests.add(ch1);

        generateFriendlyNPC(Direction.SOUTH, 17, 17, 32, 32 * 2);

        for (int i = 1; i < 8; i++) {
            walls.add(wall.createSingleWall(6, i));
        }
        for (int i = 1; i < 5; i++) {
            walls.add(wall.createSingleWall(i, 3));
        }
        for (int i = 2; i < 6; i++) {
            walls.add(wall.createSingleWall(i, 7));
        }
        for (int i = 2; i < 9; i++) {
            walls.add(wall.createSingleWall(11, i));
        }
        for (int i = 1; i < 8; i++) {
            walls.add(wall.createSingleWall(17, i));
        }

        generateObstacles(32 * 5, 32 * 3);
        generateObstacles(32 * 1, 32 * 7);
        generateObstacles(32 * 11, 32 * 1);
        generateObstacles(32 * 17, 32 * 8);

        areaConnections.add(new AreaConnection(32 * 18, 32, 32, 32));

        area = new Area(playerPosX, playerPosY, enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections);
    }

    /**
     * Non-moving friendly NPC, acting like a shop for the player.
     */
    public void generateFriendlyNPC(int direction, int width, int height, float posX, float posY) {
        FriendlyNPC friendlyNPC = new FriendlyNPC(direction, 0, width, height, posX, posY, 200, 10, 0, 0);
        friendlyNPCs.add(friendlyNPC);
    }

    /**
     * generating obstacles for this area.
     */
    public void generateObstacles(float posX, float posY) {
        obstacle = new Obstacle(posX, posY, 32, 32, 100);
        obstacles.add(obstacle);

    }

}
