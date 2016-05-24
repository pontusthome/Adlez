package com.mygdx.game.model;

import com.mygdx.game.model.characters.*;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.obstacles.*;
import org.junit.Test;

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
    private List<IWorldObject> stationaryObjects;
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
        stationaryObjects = new ArrayList<IWorldObject>();
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
     * Testing methods in Adlez.
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

        Area area = new Area(0, 0,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, name, manaFountains);
    }
}
