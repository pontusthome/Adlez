package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 03/05/16.
 */
public class Area1 implements ICompleteArea {

    private Area area;


    public Area loadArea() {
        return area;
    }

    public Area1() {
        List<IEnemy> enemies = new ArrayList<>();
        List<IFriendlyNPC> friendlyNPCs = new ArrayList<>();
        List<IWorldObject> stationaryObjects = new ArrayList<>();
        Wall wall = new Wall();
        List<IWall> walls = new ArrayList<>();
        List<IObstacle> obstacles = new ArrayList<>();
        List<IChest> chests = new ArrayList<>();
        List<IAreaConnection> areaConnections = new ArrayList<>();
        List<IManaFountain> manaFountains = new ArrayList<>();

        // Spawning left bottom corner.
        float playerPosX = 32f;
        float playerPosY = 32f;

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

        Chest ch1 = new Chest(32 * 16 + 8, 32 * 7 + 8, 16, 16, 2, 200);
        chests.add(ch1);

        friendlyNPCs.add(new FriendlyNPC(Direction.SOUTH, 17, 17, 32, 32, 32 * 2, 10, 0, 0, 0));

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

        obstacles.add(new Obstacle(5, 3));
        obstacles.add(new Obstacle(1, 7));
        obstacles.add(new Obstacle(11, 1));
        obstacles.add(new Obstacle(17, 8));

        areaConnections.add(new AreaConnection(32 * 18, 32, 32, 32));
        manaFountains.add(new ManaFountain(32*3, 32*2, 32, 32));

        area = new Area(playerPosX, playerPosY, enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, "Area1", manaFountains);
    }
}
