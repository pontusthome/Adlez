package com.mygdx.game.event;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pontus
 */
public class AreaBuilder implements AreaIO {

    /**
     * ==============================
     * Save game
     * ==============================
     */
    @Override
    public void savePlayer() {
        try (FileWriter file = new FileWriter("player.txt")) {
            AreaHandler areaHandler = AreaHandler.getInstance();
            StringBuilder jsonAreaHandler = new StringBuilder();
            jsonAreaHandler.append("{");

            // Save which area the Player is saving from
            jsonAreaHandler.append("currentArea:" + areaHandler.getCurrentAreaInt() + ",");

            // Save the individual areas
            areaToJson(areaHandler.loadArea1(), jsonAreaHandler);
            areaToJson(areaHandler.loadArea2(), jsonAreaHandler);

            jsonAreaHandler.append("}");

            file.write(jsonAreaHandler.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves data from the AreaHandler
     * Data is saved from all areas and what the current area is.
     */
    @Override
    public void saveAreaHandler() {

        try (FileWriter file = new FileWriter("areaHandler.txt")) {
            AreaHandler areaHandler = AreaHandler.getInstance();
            StringBuilder jsonAreaHandler = new StringBuilder();
            jsonAreaHandler.append("{");

            // Save which area the Player is saving from
            jsonAreaHandler.append("currentArea:" + areaHandler.getCurrentAreaInt() + ",");

            // Save the individual areas
            areaToJson(areaHandler.loadArea1(), jsonAreaHandler);
            areaToJson(areaHandler.loadArea2(), jsonAreaHandler);

            jsonAreaHandler.append("}");

            file.write(jsonAreaHandler.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforming data from an Area in JSON.
     * Saving data from enemies, obstacles and chests
     *
     * @param area the area that is turned into JSON
     * @param jsonAreaHandler a StringBuilder that the data will be stored in, it contains the JSON file for the AreaHandler
     */
    private void areaToJson(Area area, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append(area.getAreaName() + ":{");

        EnemiesToJson(area.getEnemies(), jsonAreaHandler);
        ObstaclesToJson(area.getObstacles(), jsonAreaHandler);
        ChestsToJson(area.getChests(), jsonAreaHandler);

        jsonAreaHandler.append("},");
    }

    /**
     * Transforming data from a list of enemies into JSON.
     * Saving the enemies' type and position
     *
     * @param enemies the list of enemies that are turned into JSON
     * @param jsonAreaHandler a StringBuilder that the data will be stored in, it contains the JSON file for the AreaHandler
     */
    private void EnemiesToJson(List<IEnemy> enemies, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("enemies:[");

        for (IEnemy enemy: enemies) {
            jsonAreaHandler.append("{type:" + enemy.getType() + "," +
                    "xPos:" + enemy.getPosX() + "," +
                    "yPos:" + enemy.getPosY() + "},");
        }

        jsonAreaHandler.append("],");
    }

    /**
     * Transforming data from a list of obstacles into JSON.
     * Saving the obstacles position
     *
     * @param obstacles the list of obstacles that will be turned into JSON
     * @param jsonAreaHandler a StringBuilder that the data will be stored in, it contains the JSON file for the AreaHandler
     */
    public void ObstaclesToJson(List<IObstacle> obstacles, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("obstacles:[");

        for (IObstacle obstacle: obstacles) {
            jsonAreaHandler.append("{xPos:" + obstacle.getPosX() + "," +
                    "yPos:" + obstacle.getPosY() + "},");
        }

        jsonAreaHandler.append("],");
    }

    /**
     * Transforming data from a list of chest into JSON.
     * Saving the chests' position and items
     *
     * @param chests the list of chests that will be turned into JSON
     * @param jsonAreaHandler a StringBuilder that the data will be stored in, it contains the JSON file for the AreaHandler
     */
    private void ChestsToJson(List<IChest> chests, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("chests:[");

        for (IChest chest: chests) {
            jsonAreaHandler.append("{xPos:" + chest.getPosX() + "," +
                    "yPos:" + chest.getPosY() + "," +
                    "items:" + itemsToJson(chest.getItems()) + "},");
        }

        jsonAreaHandler.append("],");
    }

    /**
     * Transforming data from a list of items into JSON.
     * Saving what kind of items they are and the items' type, name, goldValue and stats
     *
     * @param items the list of items that will be turned into JSON
     * @return String containing a JSON list of Json item objects
     */
    private String itemsToJson(List<IItem> items) {
        String jsonItems = "[";

        for (IItem item: items) {
            if (item instanceof Armor) {
                jsonItems += "{itemType:Armor,";
            }
            else if (item instanceof Weapon) {
                jsonItems += "{itemType:Weapon,";
            }
            jsonItems += "type:" + item.getType() + "," +
                    "name:" + item.getName() + "," +
                    "goldValue:" + item.getGoldValue() + "," +
                    "stats:" + item.getStats() + "},";
        }

        jsonItems += "]";
        return jsonItems;
    }

    /**
     * ==============================
     * Load game
     * ==============================
     */

    @Override
    public Player loadPlayer() {
        return null;
    }

    /**
     * Retrieves saved data in JSON format and updates the AreaHandler with the earlier game data.
     * Data that is retrieved are data about the areas and what the current area is.
     *
     * @return the AreaHandler updated with the saved data.
     */
    @Override
    public AreaHandler loadAreaHandler() {
        AreaHandler areaHandler = AreaHandler.getInstance();

        BufferedReader bufferedReader;
        try {
            String areaHandlerData;
            bufferedReader = new BufferedReader(new FileReader("areaHandler.txt"));

            while ((areaHandlerData = bufferedReader.readLine()) != null) {
                JsonValue jsonAreaHandler = new JsonReader().parse(areaHandlerData);
                //System.out.println(jsonAreaHandler.toString());

                loadArea(jsonAreaHandler.get("1"), areaHandler.loadArea1());
                loadArea(jsonAreaHandler.get("2"), areaHandler.loadArea2());

                areaHandler.setCurrentArea(jsonAreaHandler.get("currentArea").asInt());
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

    /**
     * Updates an area with saved enemies, obstacles and chests.
     *
     * @param jsonArea is the saved area data in JSON format
     * @param area is the area that is being updated with the saved data
     */
    private void loadArea(JsonValue jsonArea, Area area) {
        loadEnemies(jsonArea.get("enemies"), area);
        loadObstacles(jsonArea.get("obstacles"), area);
        loadChests(jsonArea.get("chests"), area);
    }

    /**
     * Replaces a list of enemies with saved enemies.
     *
     * @param jsonEnemies is the saved enemies' data in JSON format
     * @param area is the area that is being updated with the saved data
     */
    private void loadEnemies(JsonValue jsonEnemies, Area area) {
        List<IEnemy> enemies = area.getEnemies();
        enemies.clear();
        for (JsonValue jsonEnemy : jsonEnemies) {
            enemies.add(EnemyFactory.createEnemy(jsonEnemy.get("type").asInt(),
                    jsonEnemy.get("xPos").asFloat(),
                    jsonEnemy.get("yPos").asFloat()));
        }
    }

    /**
     * Replaces a list of obstacles with saved obstacles.
     *
     * @param jsonObstacles is the saved obstacles' data in JSON format
     * @param area is the area that is being updated with the saved data
     */
    private void loadObstacles(JsonValue jsonObstacles, Area area) {
        List<IObstacle> obstacles = area.getObstacles();
        obstacles.clear();
        for (JsonValue jsonObstacle : jsonObstacles) {
            obstacles.add(new Obstacle(jsonObstacle.get("xPos").asFloat(), jsonObstacle.get("yPos").asFloat()));
        }
    }

    /**
     * Replaces a list of chests with saved chests.
     *
     * @param jsonChests is the saved chests' data in JSON format
     * @param area is the area that is being updated with the saved data
     */
    private void loadChests(JsonValue jsonChests, Area area) {
        List<IChest> chests = area.getChests();
        chests.clear();
        for (JsonValue jsonChest : jsonChests) {
            IChest chest = new Chest(jsonChest.get("xPos").asFloat(), jsonChest.get("yPos").asFloat());
            for (IItem item : getJsonItems(jsonChest.get("items"))) {

            }
            chests.add(chest);
        }
    }

    /**
     * Returns a list of items retrieved from JSON data.
     *
     * @param jsonItems is the saved items' data in JSON format
     * @return A list of items
     */
    private List<IItem> getJsonItems(JsonValue jsonItems) {
        List items = new ArrayList();
        for (JsonValue jsonItem : jsonItems) {
            if (jsonItem.get("itemType").asString().equals("Armor")) {
                items.add(new Armor(jsonItem.get("name").asString(), jsonItem.get("type").asString(),
                        jsonItem.get("stats").asInt(), jsonItem.get("goldValue").asInt()));
            } else if (jsonItem.get("itemType").asString().equals("Weapon")) {
                items.add(new Weapon(jsonItem.get("name").asString(), jsonItem.get("type").asString(),
                        jsonItem.get("stats").asInt(), jsonItem.get("goldValue").asInt()));
            }
        }
        return items;
    }
}