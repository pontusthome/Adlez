package com.mygdx.game.model.handler;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.WorldObject;

import java.util.List;

/**
 * @author Pontus
 */

/*
 Player implements Collidable
 getBody();
 getPosition();

 onCollide(Collidable other) {

 }
 Move all characters first
 Then run update on CollisionHandler
 Loop through all collidable and if collision call onCollide() for the the ones collide.
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
