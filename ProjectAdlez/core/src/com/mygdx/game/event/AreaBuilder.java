package com.mygdx.game.event;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pontus on 2016-05-14.
 */
public class AreaBuilder implements AreaIO {

    @Override
    public void savePlayer() {
        Json json = new Json();

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("areaHanlder.txt")) {
            file.write(json.prettyPrint(Adlez.getInstance().getPlayer()));
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + json.prettyPrint(Adlez.getInstance().getPlayer()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAreaHandler() {
        Json json = new Json();

        // Save enemies, obstacles and chests

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("areaHandler.txt")) {
            file.write(json.toJson(AreaHandler.getInstance()));
            //System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + json.prettyPrint(AreaHandler.getInstance()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player loadPlayer() {
        return null;
    }

    @Override
    public AreaHandler loadAreaHandler() {
        AreaHandler areaHandler = AreaHandler.getInstance();

        Json json = new Json();
        String line = null;

        // wrap a BufferedReader around FileReader
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("areaHandler.txt"));
            // use the readLine method of the BufferedReader to read one line at a time.
            // the readLine method returns null when there is nothing else to read.
            while ((line = bufferedReader.readLine()) != null) {
                JsonValue jsonAreaHandler = new JsonReader().parse(line);

                JsonValue jsonArea1 = jsonAreaHandler.get("level1");
                loadArea(jsonArea1, areaHandler.loadArea1());
                JsonValue jsonArea2 = jsonAreaHandler.get("level2");
                loadArea(jsonArea2, areaHandler.loadArea2());

                areaHandler.setCurrentArea("Area1");
                if (jsonAreaHandler.has("currentArea")) {
                    areaHandler.setCurrentArea(jsonAreaHandler.get("currentArea").asString());
                }
            }

            // close the BufferedReader when we're done
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return areaHandler;
    }

    private void loadArea(JsonValue jsonArea, Area level1) {
        // Get the enemies, replace them with the saved enemies.
        List<IEnemy> enemies = level1.getEnemies();
        enemies.clear();
        JsonValue jsonEnemies = jsonArea.get("enemies");
        for (JsonValue jsonEnemy : jsonEnemies) {
            enemies.add(getEnemy(jsonEnemy));
        }

        List<IObstacle> obstacles = new ArrayList<>();
        List<IChest> chests = new ArrayList<>();
    }

    private IEnemy getEnemy(JsonValue jsonEnemy) {
        return new Enemy(
                jsonEnemy.get("direction").asInt(), jsonEnemy.get("speed").asFloat(),
                jsonEnemy.get("width").asInt(), jsonEnemy.get("height").asInt(),
                jsonEnemy.get("posX").asFloat(), jsonEnemy.get("posY").asFloat(),
                jsonEnemy.get("maxHealth").asInt(), jsonEnemy.get("attackDamage").asInt(),
                jsonEnemy.get("gold").asInt(), jsonEnemy.get("mana").asInt(),
                jsonEnemy.get("type").asInt()
        );
    }
}