package com.mygdx.game.event;

import com.mygdx.game.model.*;
import com.mygdx.game.model.completeAreas.Area1;
import com.mygdx.game.model.completeAreas.Area2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public class AreaHandler implements Serializable {

    private static AreaHandler areaHandler = new AreaHandler();

    public static AreaHandler getInstance() {
        return areaHandler;
    }

    private Area level1;
    private Area level2;
    private Area currentArea;

    // Create the setup for each level
    private AreaHandler() {
        createLevel1();
        createLevel2();
        currentArea = level1;
    }

    public Area loadArea1() {
        currentArea = level1;
        return level1;
    }

    public Area loadArea2() {
        currentArea = level2;
        return level2;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public void SaveAreaHandler(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
            oos.writeObject(this);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void LoadAreaHandler(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));
            areaHandler = (AreaHandler) ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createLevel1() {
        List<IEnemy> enemies = new ArrayList<>();
        List<IFriendlyNPC>friendlyNPCs = new ArrayList<>();
        List<IWorldObject>stationaryObjects = new ArrayList<>();
        Wall wall = new Wall();
        List<IWall>walls = new ArrayList<>();
        List<IObstacle>obstacles = new ArrayList<>();
        List<IChest>chests = new ArrayList<>();
        List<IAreaConnection>areaConnections = new ArrayList<>();

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

        Chest ch1 = new Chest(32 * 18 + 8, 32 * 7 + 8, 16, 16, 2, 200);
        chests.add(ch1);

        friendlyNPCs.add(new FriendlyNPC(Direction.SOUTH, 17, 17, 32, 32 * 2, 200, 10, 0, 0, 0));

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

        level1 = new Area(playerPosX, playerPosY,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, "Level1");
    }

    private void createLevel2() {
        List<IEnemy> enemies = new ArrayList<>();
        List<IFriendlyNPC>friendlyNPCs = new ArrayList<>();
        List<IWorldObject>stationaryObjects = new ArrayList<>();
        Wall wall = new Wall();
        List<IWall>walls = new ArrayList<>();
        List<IObstacle>obstacles = new ArrayList<>();
        List<IChest>chests = new ArrayList<>();
        List<IAreaConnection>areaConnections = new ArrayList<>();

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

        walls.addAll(wall.createAreaBounds(20, 10, 32));

        areaConnections.add(new AreaConnection(32 * 8, 32*18, 32, 32));

        level2 = new Area(playerPosX, playerPosY,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, "Level2");
    }
}
