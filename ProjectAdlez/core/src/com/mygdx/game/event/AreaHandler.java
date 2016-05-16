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
    private String currentArea;

    // Create the setup for each level
    private AreaHandler() {
        currentArea = "Area1";
        level1 = new Area1().loadArea();
        level2 = new Area2().loadArea();
    }

    public Area loadArea1() {
        currentArea = "Area1";
        return level1;
    }

    public Area loadArea2() {
        currentArea = "Area2";
        return level2;
    }

    public Area getCurrentArea() {
        if (currentArea.equals("Area1")) {
            return level1;
        } else {
            return level2;
        }
    }

    public void setCurrentArea(String currentArea) {
        this.currentArea = currentArea;
    }
}