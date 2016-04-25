package com.mygdx.game.model.handler;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.WorldObject;

import java.util.List;

/**
 * @author Pontus
 */
public class CollisionHandler {
    private static Adlez adlez = Adlez.getInstance();
    private static List<WorldObject> worldObjects;

    public static boolean checkCollision(WorldObject object) {
        worldObjects = adlez.getWorldObjects();

        for (WorldObject otherObject: worldObjects) {
            if (!otherObject.equals(object) && object.collide(otherObject)) {
                return true;
            }
        }
        return false;
    }
}
