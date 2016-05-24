package com.mygdx.game.model;

import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.obstacles.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Pontus
 */
public class PlayerMoveTest {

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
     * Simple method for resetting the player's stats.
     */
    public void resetPlayer() {
        adlez.resetPlayer();
        player.setSpeed(1);
        player.setPosX(0);
        player.setPosY(0);
    }

    /**
     * Test moving all directions
     * Player speed: 1
     * Delta time: 0.02
     */
    @org.junit.Test
    public void testMoveWithoutCollision() throws Exception {
        final float deltaT = 0.02f;

        Area area = new Area(0, 0,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, name, manaFountains);
        // No worldobjects
        adlez.initiateArea(area);

        // Move North.
        resetPlayer();
        player.moveNorth(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() > 0);

        // Move West.
        resetPlayer();
        player.moveWest(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() < 1);
        assertTrue(player.getPosY() == 0);

        // Move East.
        resetPlayer();
        player.moveEast(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() > 0);
        assertTrue(player.getPosY() == 0);

        // Move South.
        resetPlayer();
        player.moveSouth(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() < 0);
    }

    /**
     * Player's start position: 0, 0
     * Player moves all directions, but will stay on same place because of the wall collision.
     */
    @Test
    public void testMoveWithCollision() {
        final float deltaT = 0.02f;

        // North wall.
        Wall wallN = new Wall(0, 1);
        wallN.setPosX(1);
        wallN.setPosY(1);
        walls.add(wallN);

        // West wall.
        Wall wallW = new Wall(-1, 0);
        wallW.setPosX(1);
        wallW.setPosY(1);
        walls.add(wallW);

        // East wall.
        Wall wallE = new Wall(1, 0);
        wallE.setPosX(1);
        wallE.setPosY(1);
        walls.add(wallE);

        // South wall.
        Wall wallS = new Wall(0, -1);
        wallS.setPosX(1);
        wallS.setPosY(1);
        walls.add(wallS);

        Area area = new Area(0, 0,
                enemies, friendlyNPCs, stationaryObjects, walls, obstacles, chests, areaConnections, name, manaFountains);
        adlez.initiateArea(area);

        // Move North with collision.
        resetPlayer();
        player.moveNorth(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() == 0);

        // Move West with collision.
        resetPlayer();
        player.moveWest(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() == 0);

        // Move East with collision.
        resetPlayer();
        player.moveEast(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() == 0);

        // Move South with collision.
        resetPlayer();
        player.moveSouth(deltaT);
        player.move(deltaT);
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() == 0);
    }
}