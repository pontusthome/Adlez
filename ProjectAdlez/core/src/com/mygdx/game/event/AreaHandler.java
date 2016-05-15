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
        level1 = new Area1().loadArea();
        level2 = new Area2().loadArea();
        currentArea = level1.toString();
    }

    public Area loadArea1() {
        currentArea = level1.toString();
        return level1;
    }

    public void setArea1(Area area1) {
        this.level1 = area1;
    }

    public Area loadArea2() {
        currentArea = level2.toString();
        return level2;
    }

    public Area getCurrentArea() {
        if (currentArea.equals("Area1")) {
            return level1;
        }
        return level2;
    }

    public void SaveAreaHandler(){
        AreaIO areaBuilder = new AreaBuilder();
        areaBuilder.saveAreaHandler(this);
        /*
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
            oos.writeObject(this);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        } */
    }

    public static void LoadAreaHandler(){
        AreaIO areaBuilder = new AreaBuilder();
        areaHandler = areaBuilder.loadAreaHandler();
        /*
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));
            areaHandler = (AreaHandler) ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        */
    }
}
