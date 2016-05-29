package com.mygdx.game.model;

import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.actions.IInteraction;
import com.mygdx.game.model.actions.Interaction;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.obstacles.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 22/05/16.
 */
public class AreaConnectionTest {

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

    private AreaHandler areaHandler;

    @Before
    public void setUp() throws Exception {
        enemies = new ArrayList<IEnemy>();
        friendlyNPCs = new ArrayList<IFriendlyNPC>();
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
     * Starting area is Area_1.
     * Testing change to Area_2.
     */
    @Test
    public void testChangeArea() {
        IAreaConnection ac = new AreaConnection(40, 40, 32, 32);
        areaConnections.add(ac);

        Area area = new Area(0, 0,
                enemies, friendlyNPCs, walls, obstacles, chests, areaConnections, name, manaFountains);

        adlez.initiateArea(area);
        player = adlez.getPlayer();
        areaHandler = AreaHandler.getInstance();
        assertTrue(areaHandler.getCurrentAreaInt() == AreaHandler.AREA_1);

        IInteraction interactionPlayer = new Interaction(player);
        areaConnections.get(0).onCollide(interactionPlayer);
        // ....

    }

}
