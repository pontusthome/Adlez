package com.mygdx.game.model;

import com.mygdx.game.model.characters.*;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.obstacles.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 24/05/16.
 */
public class AdlezTest {
    private Adlez adlez = Adlez.getInstance();
    private IPlayer player;
    private List<IEnemy> enemies;
    private List<IFriendlyNPC> friendlyNPCs;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;
    private List<IAreaConnection> areaConnections;
    private int name;
    private List<IManaFountain> manaFountains;

    @org.junit.Before
    public void setUp() throws Exception {
        enemies = new ArrayList<IEnemy>();
        friendlyNPCs = new ArrayList<IFriendlyNPC>();
        walls = new ArrayList<IWall>();
        obstacles = new ArrayList<IObstacle>();
        chests = new ArrayList<IChest>();
        areaConnections = new ArrayList<IAreaConnection>();
        name = 1;
        manaFountains = new ArrayList<IManaFountain>();

        player = adlez.getPlayer();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    /**
     * Testing get methods in Adlez.
     */
    @Test
    public void testGetters() {
        // Creating an enemy.
        IEnemy enemy = new Enemy(player, Direction.NORTH, 1, 17, 17, 50, 50, 100, 10, 5, 0, Enemy.DARK_ONE_LEVEL_ONE);
        enemies.add(enemy);
        // Creating an friendly NPC.
        IFriendlyNPC friendlyNPC = new FriendlyNPC(Direction.NORTH, 1, 17, 17, 50, 50, 100, 0, 100, 0);
        friendlyNPCs.add(friendlyNPC);
        // Creating a wall.
        IWall wall = new Wall(1, 1);
        walls.add(wall);
        // Creating a obstacle.
        IObstacle obstacle = new Obstacle(20, 20, 32, 32, 100);
        obstacles.add(obstacle);
        // Creating chest.
        IChest chest = new Chest(50, 50, 5, 5, 2);
        chests.add(chest);
        // Creating area connection.
        IAreaConnection areaConnection = new AreaConnection(100, 100, 20, 20);
        areaConnections.add(areaConnection);
        // Creating mana fountain.
        IManaFountain manaFountain = new ManaFountain(100, 0, 20, 20);
        manaFountains.add(manaFountain);

        Area area = new Area(0, 0,
                enemies, friendlyNPCs, walls, obstacles, chests, areaConnections, name, manaFountains);
        adlez.initiateArea(area);

        assertTrue(adlez.getEnemies().get(0).equals(enemy));
        assertTrue(adlez.getFriendlyNPCs().get(0).equals(friendlyNPC));
        assertTrue(adlez.getWalls().get(0).equals(wall));
        assertTrue(adlez.getObstacles().get(0).equals(obstacle));
        assertTrue(adlez.getChests().get(0).equals(chest));
        assertTrue(adlez.getAreaConnections().get(0).equals(areaConnection));
        assertTrue(adlez.getManaFountains().get(0).equals(manaFountain));
    }

    /**
     * Test removing enemies from world.
     */
    @Test
    public void testRemoveEnemies() {
        // Creating an enemy.
        IEnemy enemy = new Enemy(player, Direction.NORTH, 1, 17, 17, 50, 50, 100, 10, 5, 0, Enemy.DARK_ONE_LEVEL_ONE);
        enemies.add(enemy);
        Area area = new Area(0, 0,
                enemies, friendlyNPCs, walls, obstacles, chests, areaConnections, name, manaFountains);
        adlez.initiateArea(area);

        assertTrue(adlez.getEnemies().get(0).equals(enemy));
        adlez.removeEnemyFromWorld(enemy);
        assertTrue(adlez.getEnemies().isEmpty());
    }

    @Test
    public void testRemoveObstacles() {
        // Creating a obstacle.
        IObstacle obstacle = new Obstacle(20, 20, 32, 32, 100);
        obstacles.add(obstacle);
        Area area = new Area(0, 0,
                enemies, friendlyNPCs, walls, obstacles, chests, areaConnections, name, manaFountains);
        adlez.initiateArea(area);

        assertTrue(adlez.getObstacles().get(0).equals(obstacle));
        adlez.removeObstacleFromWorld(obstacle);
        assertTrue(adlez.getObstacles().isEmpty());
    }

}
