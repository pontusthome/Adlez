package com.mygdx.game.event;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.Area;
import com.mygdx.game.model.Direction;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.WorldObject;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public final class AreaHandler {

    private static Area area;

    private static float playerXposition;
    private static float playerYposition;

    private static List<NPC> enemies;
    private static List<NPC> friendlyNPCs;
    private static List<WorldObject> stationaryObjects;

    /**
     *  If we want to save the areas as they are when the player leaves them
     *  a list of areas might help with that? /PT 24/4
     */
    public static Area testLevel() {
        playerXposition = 200;
        playerYposition = 200;

        enemies = new ArrayList<NPC>();
        friendlyNPCs = new ArrayList<NPC>();
        stationaryObjects = new ArrayList<WorldObject>();

        for (int i = 0; i < 20; i++) {
            float speed = random.nextFloat() * (2f - 1f) + 1;
            float xPos = random.nextInt(1000)-500;
            float yPos = random.nextInt(1000)-500;

            /** Set NPC's width & height to size of texture for debugging purposes */
            NPC enemy = new NPC(Direction.NORTH, speed,
                                29, 29,
                                xPos, yPos,
                                100, 5, 0, 100);
            enemies.add(enemy);
        }

        area = new Area(playerXposition, playerYposition,
                        enemies, friendlyNPCs, stationaryObjects);

        return area;
    }

}
