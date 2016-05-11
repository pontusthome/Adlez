package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 06/05/16.
 */
public class Area2 implements ICompleteArea {

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

    public Area2() {
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

        for (int i = 1; i < 5; i++) {
            walls.add(wall.createSingleWall(i, 4));
        }
        for (int i = 5; i < 18; i++) {
            walls.add(wall.createSingleWall(4, i));
        }
        for (int i = 1; i < 3; i++) {
            walls.add(wall.createSingleWall(i, 11));
        }
        for (int i = 2; i < 4; i++) {
            walls.add(wall.createSingleWall(i, 13));
        }
        for (int i = 1; i < 3; i++) {
            walls.add(wall.createSingleWall(i, 15));
        }
        for (int i = 2; i < 4; i++) {
            walls.add(wall.createSingleWall(i, 17));
        }

        generateObstacles(32*3,32*11);
        generateObstacles(32*1,32*13);
        generateObstacles(32*3,32*15);
        generateObstacles(32*1,32*17);
        generateObstacles(32*4,32*18);

        generateObstacles(32*5, 32*4);
        generateObstacles(32*6, 32*4);
        generateObstacles(32*7, 32*4);
        generateObstacles(32*8, 32*4);

        generateObstacles(32*5, 32*9);
        generateObstacles(32*6, 32*9);
        generateObstacles(32*7, 32*9);
        generateObstacles(32*8, 32*9);

        generateObstacles(32*5, 32*14);
        generateObstacles(32*6, 32*14);
        generateObstacles(32*7, 32*14);
        generateObstacles(32*8, 32*14);

        generateObstacles(32*7, 32*17);
        generateObstacles(32*8, 32*17);

        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 5, 32 * 6));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 6, 32 * 6));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 7, 32 * 6));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 32 * 8, 32 * 6));

        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 5, 32 * 7));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 6, 32 * 7));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 7, 32 * 7));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 8, 32 * 7));

        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 5, 32 * 11));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 6, 32 * 11));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 7, 32 * 11));
        enemies.add(EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_TWO, 32 * 8, 32 * 11));

        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 5, 32 * 12));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 6, 32 * 12));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 7, 32 * 12));
        enemies.add(EnemyFactory.createEnemy(Enemy.DARK_ONE_LEVEL_ONE, 32 * 8, 32 * 12));

        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 6, 32 * 15));
        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 7, 32 * 15));

        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 2, 32 * 7));
        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 2, 32 * 8));
        enemies.add(EnemyFactory.createEnemy(Enemy.DOG_LEVEL_ONE, 32 * 2, 32 * 9));

        Chest ch1 = new Chest(32 * 2 + 8, 32 * 5 + 8, 16, 16, 2, 200);
        chests.add(ch1);

        walls.addAll(wall.createAreaBounds(20, 10, 32));

        areaConnections.add(new AreaConnection(32 * 8, 32*18, 32, 32));

        generateFriendlyNPC(Direction.SOUTH, 25, 25, 32, 32 * 3);

        area = new Area(playerPosX, playerPosY, enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, "Area2");
    }

    /**
     * Non-moving friendly NPC, acting like a shop for the player.
     */
    public void generateFriendlyNPC(int direction, int width, int height, float posX, float posY) {
        FriendlyNPC friendlyNPC = new FriendlyNPC(direction, 0, width, height, posX, posY, 200, 10, 0, 0);
        friendlyNPCs.add(friendlyNPC);
    }

    /**
     * generating walls not included in area bounds.
     */
    public void generateSingleWall(int posX, int posY, int size) {
        walls.add(wall.createSingleWall(posX, posY));
    }

    /**
     * generating obstacles for this area.
     */
    public void generateObstacles(float posX, float posY) {
        obstacle = new Obstacle(posX, posY, 32, 32, 100);
        obstacles.add(obstacle);

    }

}

