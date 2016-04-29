package com.mygdx.game.model.handler;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IWorldObject;

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
    private static List<IWorldObject> worldObjects;

    public static boolean checkCollision(IWorldObject object) {
        worldObjects = adlez.getWorldObjects();

        for (IWorldObject otherObject: worldObjects) {
            if (!otherObject.equals(object) && object.collide(otherObject)) {
                return true;
            }
        }
        return false;
    }
}
