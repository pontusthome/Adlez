package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 06/05/16.
 */
public class Area2 implements ICompleteArea {

    private Area area;

    public Area loadArea() {
        return area;
    }

    public Area2() {
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

        obstacles.add(new Obstacle(3, 11));
        obstacles.add(new Obstacle(1, 13));
        obstacles.add(new Obstacle(3, 15));
        obstacles.add(new Obstacle(1, 17));
        obstacles.add(new Obstacle(4, 18));

        obstacles.add(new Obstacle(5, 4));
        obstacles.add(new Obstacle(6, 4));
        obstacles.add(new Obstacle(7, 4));
        obstacles.add(new Obstacle(8, 4));

        obstacles.add(new Obstacle(5, 9));
        obstacles.add(new Obstacle(6, 9));
        obstacles.add(new Obstacle(7, 9));
        obstacles.add(new Obstacle(8, 9));

        obstacles.add(new Obstacle(5, 14));
        obstacles.add(new Obstacle(6, 14));
        obstacles.add(new Obstacle(7, 14));
        obstacles.add(new Obstacle(8, 14));

        obstacles.add(new Obstacle(7, 17));
        obstacles.add(new Obstacle(8, 17));

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

        friendlyNPCs.add(new FriendlyNPC(Direction.SOUTH, 17, 17, 32, 32, 32 * 3, 10, 0, 0, 0));

        walls.addAll(wall.createAreaBounds(20, 10, 32));

        areaConnections.add(new AreaConnection(32 * 8, 32 * 18, 32, 32));
        manaFountains.add(new ManaFountain(32*3, 32*3, 32, 32));

        area = new Area(playerPosX, playerPosY, enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, "Area2", manaFountains);
    }
}

