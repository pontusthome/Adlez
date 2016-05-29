package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Michel on 28.4.2016.
 */

public final class AssetStrings {

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "skins/statusui.atlas";
    private final static String STATUSUI_SKIN_PATH = "skins/statusui.json";
    private final static String ITEMS_TEXTURE_ATLAS_PATH = "skins/items.atlas";
    private final static String ITEMS_SKIN_PATH = "skins/items.json";

    public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(AssetStrings.STATUSUI_TEXTURE_ATLAS_PATH);
    public static TextureAtlas ITEMS_TEXTUREATLAS = new TextureAtlas(AssetStrings.ITEMS_TEXTURE_ATLAS_PATH);

    public static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(AssetStrings.STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);



    /**
     * Buttons
     */
    public static final String NEW_GAME_BUTTON_IMAGE = "buttons/newGameButton.9.png";
    public static final String LOAD_GAME_BUTTON_IMAGE = "buttons/loadGameButton.9.png";
    public static final String EXIT_GAME_BUTTON_IMAGE = "buttons/exitGameButton.9.png";
    public static final String INVENTORY_BUTTON_IMAGE = "buttons/inventoryButton.9.png";
    public static final String SAVE_GAME_BUTTON_IMAGE = "buttons/saveGameButton.9.png";
    public static final String SETTINGS_BUTTON_IMAGE = "buttons/settingsButton.9.png";
    public static final String MAIN_MENU_BUTTON_IMAGE = "buttons/mainMenuButton.9.png";

    /**
     * Sounds
     */
    public static final String MELEE_ATTACK_SOUND = "sounds/punch2.wav";
    public static final String AOE_MAGIC_ATTACK_SOUND = "sounds/fire_attack.wav";
    public static final String RANGE_MAGIC_ATTACK_SOUND = "sounds/ice_attack.wav";
    public static final String OUT_OF_MANA_SOUND = "sounds/out_of_mana.wav";
    public static final String INTERACTION_SOUND = "sounds/interaction.wav";
    public static final String TEMP_DYING_SOUND = "sounds/temp_dying_sound.wav";


    /* Backgrounds */
    public static final String MAIN_MENU_BACKGROUND_IMAGE = "backgrounds/mainMenuBackground2.png";
    public static final String INTRO_SCREEN_BACKGROUND_IMAGE = "backgrounds/ZeldaIntro.png";
    public static final String GAME_OVER_BACKGROUND_IMAGE = "backgrounds/gameoverBackground.png";

    /* Stationary obstacles */
    public static final String BOX_OBSTACLE_IMAGE = "obstacles/boxObstacle.jpeg";
    public static final String CLOSED_CHEST_IMAGE = "obstacles/closed_chest.png";
    public static final String OPEN_CHEST_IMAGE = "obstacles/open_chest.png";     // Same as closed chest for now
    public static final String BOULDER_OBSTACLE_IMAGE = "obstacles/boulder_32x32.png";
    public static final String DOOR_GATE_IMAGE = "obstacles/door_32x32.png";
    public static final String MANA_FOUNTAIN_IMAGE = "obstacles/manaFountain_32x32.png";

    /* Sprites */
    public static final String PLAYER_MOVE = "player/moveSpriteV2.png";
    public static final String RED_PLAYER_MOVE = "npc/redPlayer.png";
    public static final String BLUE_PLAYER_MOVE = "npc/bluePlayer.png";
    public static final String DARK_BLUE_PLAYER_MOVE = "npc/darkBluePlayer.png";
    public static final String BLUE_DOG_MOVE = "npc/Azurewalk.png";
    public static final String FRIENDLY_NPC_SOUTH = "npc/friendlyNPC.png";

    /**
     * Item Images
     */
    public static final String WOOD_SWORD = "items/wood_sword_32x32.png";
    public static final String IRON_SWORD = "items/iron_sword_32x32.png";
    public static final String FINAL_SWORD = "items/final_sword_32x32.png";

    public static final String CLOTH_BODY_ARMOR = "items/cloth_body_armor_32x32.png";
    public static final String IRON_BODY_ARMOR = "items/iron_body_armor_32x32.png";
    public static final String FINAL_BODY_ARMOR = "items/final_body_armor_32x32.png";
    public static final String INVENTORY_SLOI = "items/inventorySlot.png";

    /**
     * Areas
     */
    public static final String TEST_LEVEL_TMX = "areas/testLevel1.tmx";

    public static final String AREA1_TMX = "areas/area1.tmx";
    public static final String AREA2_TMX = "areas/area2.tmx";
}
