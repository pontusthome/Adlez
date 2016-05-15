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
    private Area currentArea;

    public static final int AREA_1 = 1;
    public static final int AREA_2 = 2;

    // Create the setup for each level
    private AreaHandler() {
        level1 = new Area1().loadArea();
        level2 = new Area2().loadArea();
        currentArea = level1;
    }

    public Area loadArea1() {
        currentArea = level1;
        return level1;
    }

    public Area loadArea2() {
        currentArea = level2;
        return level2;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public int getCurrentAreaInt() {
        if(currentArea == level1) {
            return AREA_1;
        } else if(currentArea == level2) {
            return AREA_2;
        } else {
            return 0;
        }
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

    public static void LoadAreaHandler(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));
            areaHandler = (AreaHandler) ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
