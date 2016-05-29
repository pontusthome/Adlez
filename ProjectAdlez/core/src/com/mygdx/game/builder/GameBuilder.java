package com.mygdx.game.builder;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.model.*;
import com.mygdx.game.model.factories.EnemyFactory;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.Area;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.items.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pontus
 */
public class GameBuilder implements GameIO {

    /**
     * ==============================
     * Save and Load player
     * ==============================
     */

    /**
     * Saves all the data in JSON that the player has including the items
     */
    @Override
    public void savePlayer() throws IOException {
        FileWriter file = new FileWriter("player.txt");
        IPlayer player = Adlez.getInstance().getPlayer();
        StringBuilder jsonPlayer = new StringBuilder();
        jsonPlayer.append("{");
        addJsonAttribute(jsonPlayer, "name", player.getName());
        addJsonAttribute(jsonPlayer, "xPos", player.getPosX());
        addJsonAttribute(jsonPlayer, "yPos", player.getPosY());
        addJsonAttribute(jsonPlayer, "height", player.getHeight());
        addJsonAttribute(jsonPlayer, "width", player.getWidth());
        addJsonAttribute(jsonPlayer, "speed", player.getSpeed());
        addJsonAttribute(jsonPlayer, "maxHealth", player.getMaxHealth());
        addJsonAttribute(jsonPlayer, "health", player.getHealth());
        addJsonAttribute(jsonPlayer, "maxMana", player.getMaxMana());
        addJsonAttribute(jsonPlayer, "mana", player.getMana());
        addJsonAttribute(jsonPlayer, "attackDamage", player.getAttackDamage());
        addJsonAttribute(jsonPlayer, "direction", player.getDirection());
        addJsonAttribute(jsonPlayer, "gold", player.getGold());
        addJsonAttribute(jsonPlayer, "level", player.getLevel());
        if (player.getArmorEquipped() != null) {
            addJsonAttribute(jsonPlayer, "armorEquipped", itemToJson(player.getArmorEquipped()));
        }
        if (player.getSwordEquipped() != null) {
            addJsonAttribute(jsonPlayer, "swordEquipped", itemToJson(player.getSwordEquipped()));
        }
        addJsonAttribute(jsonPlayer, "inventory", itemsToJson(player.getInventory()));

        jsonPlayer.append("}");

        file.write(jsonPlayer.toString());
        file.flush();
        file.close();
    }

    /**
     * Loads the saved player data and sets it to the player
     *
     * @return the saved player
     */
    @Override
    public IPlayer loadPlayer() throws IOException, ItemNotFoundException, InventoryFullException {
        IPlayer player = Adlez.getInstance().getPlayer();

        BufferedReader bufferedReader;
        String areaHandlerData;
        bufferedReader = new BufferedReader(new FileReader("player.txt"));

        while ((areaHandlerData = bufferedReader.readLine()) != null) {
            JsonValue jsonPlayer = new JsonReader().parse(areaHandlerData);

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
                player.lootItem(item);
            }
        }

        // close the BufferedReader when we're done
        bufferedReader.close();

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
    public void saveAreaHandler() throws IOException {

        FileWriter file = new FileWriter("areaHandler.txt");
        AreaHandler areaHandler = AreaHandler.getInstance();
        StringBuilder jsonAreaHandler = new StringBuilder();
        jsonAreaHandler.append("{");

        // Save which area the Player is saving from
        addJsonAttribute(jsonAreaHandler, "currentArea", areaHandler.getCurrentAreaInt());

        // Save the individual areas
        areaToJson(areaHandler.loadArea1(), jsonAreaHandler);
        areaToJson(areaHandler.loadArea2(), jsonAreaHandler);

        jsonAreaHandler.append("}");

        file.write(jsonAreaHandler.toString());
        file.flush();
        file.close();

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

        enemiesToJson(area.getEnemies(), jsonAreaHandler);
        obstaclesToJson(area.getObstacles(), jsonAreaHandler);
        chestsToJson(area.getChests(), jsonAreaHandler);

        jsonAreaHandler.append("},");
    }

    /**
     * Transforming data from a list of enemies into JSON.
     * Saving the enemies' type and position
     *
     * @param enemies the list of enemies that are turned into JSON
     * @param jsonAreaHandler a StringBuilder that the data will be stored in, it contains the JSON file for the AreaHandler
     */
    private void enemiesToJson(List<IEnemy> enemies, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("enemies:[");

        for (IEnemy enemy: enemies) {
            jsonAreaHandler.append("{");
            addJsonAttribute(jsonAreaHandler, "type", enemy.getType());
            addJsonAttribute(jsonAreaHandler, "xPos", enemy.getPosX());
            addJsonAttribute(jsonAreaHandler, "yPos", enemy.getPosY());
            jsonAreaHandler.append("},");
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
    private void obstaclesToJson(List<IObstacle> obstacles, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("obstacles:[");

        for (IObstacle obstacle: obstacles) {
            jsonAreaHandler.append("{");
            addJsonAttribute(jsonAreaHandler, "xPos", obstacle.getPosX());
            addJsonAttribute(jsonAreaHandler, "yPos", obstacle.getPosY());
            jsonAreaHandler.append("},");
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
    private void chestsToJson(List<IChest> chests, StringBuilder jsonAreaHandler) {
        jsonAreaHandler.append("chests:[");

        for (IChest chest: chests) {
            jsonAreaHandler.append("{");
            addJsonAttribute(jsonAreaHandler, "xPos", chest.getPosX());
            addJsonAttribute(jsonAreaHandler, "yPos", chest.getPosY());
            addJsonAttribute(jsonAreaHandler, "items", itemsToJson(chest.getItems()));
            addJsonAttribute(jsonAreaHandler, "isOpened", chest.isOpened());
            jsonAreaHandler.append("},");
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


    private void addJsonAttribute(StringBuilder JsonObject, String name, Object value) {
        JsonObject.append(name + ":" + value + ",");
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
    public AreaHandler loadAreaHandler() throws IOException, InventoryFullException {
        AreaHandler areaHandler = AreaHandler.getInstance();

        BufferedReader bufferedReader;
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

        return areaHandler;
    }

    /**
     * Updates an area with saved enemies, obstacles and chests.
     *
     * @param jsonArea is the saved area data in JSON format
     * @param area is the area that is being updated with the saved data
     */
    private void loadArea(JsonValue jsonArea, Area area) throws InventoryFullException {
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
    private void loadChests(JsonValue jsonChests, Area area) throws InventoryFullException {
        List<IChest> chests = area.getChests();
        chests.clear();
        for (JsonValue jsonChest : jsonChests) {
            IChest chest = new Chest(jsonChest.get("xPos").asFloat(), jsonChest.get("yPos").asFloat(), 16, 16, 2);
            for (IItem item : getJsonItems(jsonChest.get("items"))) {
                chest.addItem(item);
            }
            chest.setIsOpened(jsonChest.get("isOpened").asBoolean());
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