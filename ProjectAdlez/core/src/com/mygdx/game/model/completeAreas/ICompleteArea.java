package com.mygdx.game.model.completeAreas;

import com.mygdx.game.model.Area;

/**
 * Created by martinso on 06/05/16.
 */
public interface ICompleteArea {
    public Area loadArea();
    public void generateFriendlyNPC(int direction, int width, int height, float posX, float posY);
    public void generateSingleWall(float posX, float posY, int size);
    public void generateObstacles(float posX, float posY);
}
