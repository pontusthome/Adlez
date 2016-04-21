package com.mygdx.game.controller;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.WorldObject;

import java.util.List;

/**
 * @author Pontus
 */
public class CollisionHandler {
    private Adlez adlez = Adlez.getInstance();
    private List<WorldObject> worldObjectList = adlez.getWorldObjects();

    public boolean checkCollision(WorldObject object) {
        for (WorldObject otherObject: worldObjectList) {
            if (!otherObject.equals(object) && object.collide(otherObject)) {
                return true;
            }
        }
        return false;
    }
}
