package com.mygdx.game.CollisionHandler;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;
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

    public static void checkCollision() {
        worldObjects = adlez.getWorldObjects();

        for (int i = 0; i < worldObjects.size(); i++ ) {
            for (int j = i+1; j < worldObjects.size(); j++) {
                if (collide(worldObjects.get(i), worldObjects.get(j))) {
                    worldObjects.get(i).onCollide(worldObjects.get(j));
                    worldObjects.get(j).onCollide(worldObjects.get(i));
                }
            }
        }
    }

    public static boolean collide(WorldObject object1, WorldObject object2) {
        float width = object1.getWidth();
        float height = object1.getHeight();
        float otherWidth = object2.getWidth();
        float otherHeight = object2.getHeight();
        if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
            return false;
        }
        float x = object1.getPosX();
        float y = object1.getPosY();
        float otherX = object2.getPosX();
        float otherY = object2.getPosY();
        otherWidth += otherX;
        otherHeight += otherY;
        width += x;
        height += y;
        //      overflow || intersect
        return ((otherWidth < otherX || otherWidth > x) &&
                (otherHeight < otherY || otherHeight > y) &&
                (width < x || width > otherX) &&
                (height < y || height > otherY));
    }
}
