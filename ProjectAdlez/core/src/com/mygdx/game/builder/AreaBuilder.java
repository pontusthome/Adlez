package com.mygdx.game.builder;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.model.*;
import com.mygdx.game.model.factories.EnemyFactory;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.Area;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.characters.items.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pontus
 */
public class AreaBuilder implements AreaIO {

    /**
     * ==============================
     * Save and Load player
     * ==============================
     */

    /**
     * Saves all the data in JSON that the player has including the items
     */
    @Override
    public void savePlayer() {
        try (FileWriter file = new FileWriter("player.txt")) {
            IPlayer player = Adlez.getInstance().getPlayer();
            StringBuilder jsonPlayer = new StringBuilder();
            jsonPlayer.append("{");

            jsonPlayer.append("name" + ":" + player.getName() + ",");
            jsonPlayer.append("xPos" + ":" + player.getPosX() + ",");
            jsonPlayer.append("yPos" + ":" + player.getPosY() + ",");
            jsonPlayer.append("height" + ":" + player.getHeight() + ",");
            jsonPlayer.append("width" + ":" + player.getWidth() + ",");
            jsonPlayer.append("speed" + ":" + player.getSpeed() + ",");
            jsonPlayer.append("maxHealth" + ":" + player.getMaxHealth() + ",");
            jsonPlayer.append("health" + ":" + player.getHealth() + ",");
            jsonPlayer.append("maxMana" + ":" + player.getMaxMana() + ",");
            jsonPlayer.append("mana" + ":" + player.getMana() + ",");
            jsonPlayer.append("attackDamage" + ":" + player.getAttackDamage() + ",");
            jsonPlayer.append("direction" + ":" + player.getDirection() + ",");
            jsonPlayer.append("gold" + ":" + player.getGold() + ",");
            jsonPlayer.append("level" + ":" + player.getLevel() + ",");
            if (player.getArmorEquipped() != null) {
                jsonPlayer.append("armorEquipped" + ":" + itemToJson(player.getArmorEquipped()) + ",");
            }
            if (player.getSwordEquipped() != null) {
                jsonPlayer.append("swordEquipped" + ":" + itemToJson(player.getSwordEquipped()) + ",");
            }
            jsonPlayer.append("inventory" + ":" + itemsToJson(player.getInventory()) + ",");

            jsonPlayer.append("}");

            file.write(jsonPlayer.toString());
        } catch (IOException e) {
            System.out.println("Cannot save");
        }
    }

    /**
     * Loads the saved player data and sets it to the player
     *
     * @return the saved player
     */
    @Override
    public IPlayer loadPlayer() {
        IPlayer player = Adlez.getInstance().getPlayer();

        BufferedReader bufferedReader;
        try {
            String areaHandlerData;
            bufferedReader = new BufferedReader(new FileReader("player.txt"));

            while ((areaHandlerData = bufferedReader.readLine()) != null) {
                JsonValue jsonPlayer = new JsonReader().parse(areaHandlerData);
                //System.out.println(jsonAreaHandler.toString());

                player.setName(jsonPlayer.get("name").asString());
                player.setPos(jsonPlayer.get("xPos").asFloat(), jsonPlayer.get("yPos").asFloat());
                player.setWidth(jsonPlayer.get("width").asInt());
                player.setHeight(jsonPlayer.get("height").asInt());
                player.setSpeed(jsonPlayer.get("speed").asFloat());
                player.setMaxHealth(jsonPlayer.get("maxHealth").asInt());
                player.setHealth(jsonPlayer.get("health").asInt());
                player.setMaxMana(jsonPlayer.get("maxMana").asInt());
                player.setMana(jsonPlayer.get("mana").asInt());
                player.setAttackDamage(jsonPlayer.get("attackDamage").asInt());
                player.setDirection(jsonPlayer.get("direction").asInt());
                player.setGold(jsonPlayer.get("gold").asInt());
                player.setLevel(jsonPlayer.get("level").asInt());
                if (jsonPlayer.get("armorEquipped") != null) {
                    player.equipItem(getJsonItem(jsonPlayer.get("armorEquipped")));
                }
                if (jsonPlayer.get("swordEquipped") != null) {
                    player.equipItem(getJsonItem(jsonPlayer.get("swordEquipped")));
                }
                for (IItem item: getJsonItems(jsonPlayer.get("inventory"))) {
                    try {
                        player.lootItem(item);
                    } catch (InventoryFullException e) {
                        e.printStackTrace();
                    }
                }
            }

            // close the BufferedReader when we're done
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Cannot load saved player");
        }

        return player;
    }

    /**
     * ==================================
     * Save the AreaHandler
     * ==================================
     */

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
            jsonAreaHandler.append("currentArea" + ":" + areaHandler.getCurrentAreaInt() + ",");

            // Save the individual areas
            areaToJson(areaHandler.loadArea1(), jsonAreaHandler);
            areaToJson(areaHandler.loadArea2(), jsonAreaHandler);

            jsonAreaHandler.append("}");

            file.write(jsonAreaHandler.toString());
        } catch (IOException e) {
            System.out.println("Cannot save");
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
            jsonAreaHandler.append("{type" + ":" + enemy.getType() + "," +
                    "xPos" + ":" + enemy.getPosX() + "," +
                    "yPos" + ":" + enemy.getPosY() + "},");
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
            jsonAreaHandler.append("{xPos" + ":" + obstacle.getPosX() + "," +
                    "yPos" + ":" + obstacle.getPosY() + "},");
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
            jsonAreaHandler.append("{xPos" + ":" + chest.getPosX() + "," +
                    "yPos" + ":" + chest.getPosY() + "," +
                    "items" + ":" + itemsToJson(chest.getItems()) + "," +
                    "isOpened" + ":" + chest.isOpened() + "}," );
        }

        jsonAreaHandler.append("],");
    }

    /**
     * Transforming data from a list of items into JSON.
     * Saving what kind of items they are and the items' type, name, goldValue and stats
     *
     * @param items the list of items that will be turned into JSON
     * @return String containing a JSON list of Json item obstacles
     */
    private String itemsToJson(List<IItem> items) {
        String jsonItems = "[";

        for (IItem item: items) {
            jsonItems += itemToJson(item);
        }

        jsonItems += "]";
        return jsonItems;
    }

    /**
     * Creates a JSON object from an item
     *
     * @param item the item that is turned into a JSON obeject
     * @return item as a JSON object
     */
    private String itemToJson(IItem item) {
        String jsonItem = "";

        if (item != null) {
            if (item instanceof Armor) {
                jsonItem += "{itemType:Armor,";
            } else if (item instanceof Weapon) {
                jsonItem += "{itemType:Weapon,";
            }
            jsonItem += "type" + ":" + item.getType() + "," +
                    "name" + ":" + item.getName() + "," +
                    "goldValue" + ":" + item.getGoldValue() + "," +
                    "stats" + ":" + item.getStats() + "},";
        }

        return jsonItem;
    }

    /**
     * ==============================
     * Load AreaHandler
     * ==============================
     */

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
        } catch (Exception e) {
            System.out.println("Cannot find saved game");
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
            chest.setIsOpened(jsonChest.get("isOpened").isBoolean());
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
            items.add(getJsonItem(jsonItem));
        }
        return items;
    }

    /**
     * Returns an item retrieved from JSON data.
     *
     * @param jsonItem is the saved item's data in JSON format
     * @return An item
     */
    private IItem getJsonItem(JsonValue jsonItem) {
        if (jsonItem.get("itemType").asString().equals("Armor")) {
            return new Armor(jsonItem.get("name").asString(), jsonItem.get("type").asString(),
                    jsonItem.get("stats").asInt(), jsonItem.get("goldValue").asInt());
        } else if (jsonItem.get("itemType").asString().equals("Weapon")) {
             return new Weapon(jsonItem.get("name").asString(), jsonItem.get("type").asString(),
                    jsonItem.get("stats").asInt(), jsonItem.get("goldValue").asInt());
        }
        return null;
    }
}