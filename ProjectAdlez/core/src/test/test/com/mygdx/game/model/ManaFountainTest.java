package com.mygdx.game.model;

import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.characters.actions.Interaction;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.obstacles.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by martinso on 22/05/16.
 */
public class ManaFountainTest {

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

    private AreaHandler areaHandler;

    @Before
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
    }

    @After
    public void tearDown() throws Exception {

    }
        /**
         * Regenerate players mana.
         */
        @Test
        public void testManaFountain () {
            //IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
            IManaFountain manaFountain = new ManaFountain(50, 50, 32, 32);
            manaFountains.add(manaFountain);
            Area area = new Area(0, 0,
                    enemies, friendlyNPCs, walls, obstacles, chests, areaConnections, name, manaFountains);
            adlez.initiateArea(area);
            player = adlez.getPlayer();
            player.setMana(player.getMana() - 100);
            assertTrue(player.getMana() == 0);

            IInteraction interactionPlayer = new Interaction(player);
            manaFountains.get(0).onCollide(interactionPlayer);
            assertTrue(player.getMana() == 100);
        }
    }
