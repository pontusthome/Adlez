package com.mygdx.game.event;

import com.mygdx.game.model.*;
import com.mygdx.game.model.completeAreas.Area1;
import com.mygdx.game.model.completeAreas.Area2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public class AreaHandler implements Serializable {

    private static AreaHandler areaHandler = new AreaHandler();

    public static AreaHandler getInstance() {
        return areaHandler;
    }

    private Area level1;
    private Area level2;
    private int currentArea;

    public static final int AREA_1 = 1;
    public static final int AREA_2 = 2;

    // Create the setup for each level
    private AreaHandler() {
        currentArea = AREA_1;
        level1 = new Area1().loadArea();
        level2 = new Area2().loadArea();
    }

    public Area loadArea1() {
        currentArea = AREA_1;
        return level1;
    }

    public Area loadArea2() {
        currentArea = AREA_2;
        return level2;
    }

    public Area getCurrentArea() {
        if (currentArea == AREA_1) {
            return level1;
        } else {
            return level2;
        }
    }

    public int getCurrentAreaInt() {
        return currentArea;
    }

    public void SaveAreaHandler(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
            oos.writeObject(this);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setCurrentArea(int currentArea) {
        this.currentArea = currentArea;
    }
}