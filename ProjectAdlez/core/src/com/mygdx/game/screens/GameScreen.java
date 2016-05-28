package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import com.mygdx.game.controller.*;
import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.model.*;
import com.mygdx.game.model.characters.*;
import com.mygdx.game.model.characters.actions.HitBox;
import com.mygdx.game.model.characters.actions.IAttack;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.collisionHandler.CollisionHandler;
import com.mygdx.game.sound.GameSound;
import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.sound.LibGDXSoundAdapter;
import com.mygdx.game.model.obstacles.IAreaConnection;
import com.mygdx.game.model.obstacles.IWall;
import com.mygdx.game.utils.AssetStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor on 2016-04-19.
 */
public class GameScreen extends AbstractScreen implements GateOpenListener, ShopOpenListener{

    private Adlez adlez = Adlez.getInstance();

    private AreaHandler areaHandler = AreaHandler.getInstance();

    private IPlayer player;
    private ICharacterController playerController;
    private OrthographicCamera playerCam;

    private HashMap<IEnemy, ICharacterController> enemies;

    private SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
    private TiledMap tileMap;
    
    private CollisionHandler collisionHandler;

    private HashMap<IAttack, IController> attackControllers;
    private ShapeRenderer debugRenderer = new ShapeRenderer();
    private List<IAttack> newAttacks;
    
    private IController obstaclesController;
    private IController chestsController;
    private IController friendlyNPCController;
    private IController areaConnectionController;
    private IController manaFountainController;
    private HashMap<IInteraction, IController> interactionControllers;
    private List<IInteraction> newInteractions;
    
    private static final float UNIT_SCALE = 1f;
    private static final float WIDTH_SCALE = 2/3f;
    private static final float HEIGHT_SCALE = 2/3f;

    private OrthographicCamera hudCamera = null;
    private PlayerHUD playerHUD;

    public GameScreen() {
        super();
        batch = new SpriteBatch();
    }

    @Override
    public void buildStage() {
    
        collisionHandler = adlez.getCollisionHandler();
        
        // Creating camera
        playerCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        // Spawning player.
        player = adlez.getPlayer();
        playerController = new PlayerController(player);

        //Setting up the player HUD
        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(
                false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerHUD = new PlayerHUD(hudCamera, player);
        batch.setProjectionMatrix(playerHUD.getStage().getCamera().combined);
        playerHUD.getStage().draw();

        // Spawning enemies.
        enemies = new HashMap<>();
        for (IEnemy enemy: adlez.getEnemies()) {
            EnemyController enemyController = new EnemyController(enemy);
            enemies.put(enemy, enemyController);
        }
        
        attackControllers = new HashMap<>();
        for (IAttack attack : adlez.getAttacks()) {
            AttackController attackController = new AttackController(attack);
            attackControllers.put(attack, attackController);
        }

        newAttacks = adlez.getNewAttacks();
    
        obstaclesController = new ObstaclesController(adlez.getObstacles(), AssetStrings.BOULDER_OBSTACLE_IMAGE);
        chestsController = new ChestsController(adlez.getChests(), AssetStrings.CLOSED_CHEST_IMAGE, AssetStrings.OPEN_CHEST_IMAGE);
        friendlyNPCController = new FriendlyNPCController(adlez.getFriendlyNPCs(), AssetStrings.FRIENDLY_NPC_SOUTH);
        areaConnectionController = new AreaConnectionController(adlez.getAreaConnections(), AssetStrings.DOOR_GATE_IMAGE);
        manaFountainController = new ManaFountainController(adlez.getManaFountains(), AssetStrings.MANA_FOUNTAIN_IMAGE);

        List<IAreaConnection> areaConnections = adlez.getAreaConnections();
        for (IAreaConnection ac : areaConnections) {
            ac.add(this);
        }

        List<IFriendlyNPC> friendlyNPCs = adlez.getFriendlyNPCs();
        for (IFriendlyNPC fNPC : friendlyNPCs) {
            fNPC.add(this);
        }

        interactionControllers = new HashMap<>();
        newInteractions = adlez.getNewInteractions();

        if(areaHandler.getCurrentAreaInt() == AreaHandler.AREA_1) {
            tileMap = new TmxMapLoader().load(AssetStrings.AREA1_TMX);
        } else if(areaHandler.getCurrentAreaInt() == AreaHandler.AREA_2) {
            tileMap = new TmxMapLoader().load(AssetStrings.AREA2_TMX);
        }
        float unitScale = UNIT_SCALE;

        renderer = new OrthoCachedTiledMapRenderer(tileMap, unitScale);
        playerCam.setToOrtho(false, Gdx.graphics.getWidth() * WIDTH_SCALE, 
                Gdx.graphics.getHeight() * HEIGHT_SCALE);
    }

    @Override
    public void render(float delta) {
        updateGame(delta);
        playerHUD.update();

        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render Tiled map
        renderer.setView(playerCam);
        renderer.render();

        batch.setProjectionMatrix((playerCam.combined));
        batch.begin();

        // Update camera
        playerCam.update();
        playerCam.position.set(player.getPosX() + (playerController.getView().getCurrentFrame().getRegionWidth() / 2),
                player.getPosY() + (playerController.getView().getCurrentFrame().getRegionHeight() / 2),
                0); // z = 0, non 3D

        renderWorldObjects();
        
        batch.end();
        
        // Render HUD
        playerHUD.render(delta);
    
        //debugRender();
    }

    private void updateGame(float delta) {

        // Updating player
        checkIfPlayerDied();
        playerController.update(delta);

        updateEnemies(delta);
        updateAttacks(delta);
        updateInteractions(delta);

        // Update stationary obstacles
        obstaclesController.update(delta);
        chestsController.update(delta);

        collisionHandler.updateAttacks();
        collisionHandler.updateInteractions();
    }

    private void updateEnemies(float delta) {
        // Remove the old enemyControllers by saving the old list of controllers
        // and only saving the enemyControllers for the enemies that still exist.
        HashMap<IEnemy, ICharacterController> oldEnemies = (HashMap<IEnemy, ICharacterController>) enemies.clone();
        enemies.clear();
        for (IEnemy enemy: adlez.getEnemies()) {
            ICharacterController enemyController = oldEnemies.get(enemy);
            enemies.put(enemy, enemyController);
        }
        // Updating the enemies.
        for(Map.Entry<IEnemy, ICharacterController> entry : enemies.entrySet()) {
            ICharacterController enemyController = entry.getValue();
            enemyController.update(delta);
        }
    }

    private void updateAttacks(float delta) {
        if(!newAttacks.isEmpty()){
            for(IAttack attack : newAttacks){
                AttackController attackController = new AttackController(attack);
                attackControllers.put(attack, attackController);
            }
            newAttacks.clear();
        }
        List<IAttack> finishedAttacks = new ArrayList<>();
        for(Map.Entry<IAttack, IController> entry : attackControllers.entrySet()){
            IAttack attack = entry.getKey();
            IController attackController = entry.getValue();

            if (attack.isFinished()) {
                finishedAttacks.add(attack);
            }
            else {
                attackController.update(delta);
            }
        }
        for (IAttack finishedAttack: finishedAttacks) {
            attackControllers.remove(finishedAttack);
            adlez.removeAttackFromWorld(finishedAttack);
        }

    }

    private void updateInteractions(float delta) {
        if(!newInteractions.isEmpty()){
            for(IInteraction interaction : newInteractions){
                InteractionController interactionController = new InteractionController(interaction);
                interactionControllers.put(interaction, interactionController);
            }
            newInteractions.clear();
        }
        List<IInteraction> finishedInteractions = new ArrayList<>();
        for(Map.Entry<IInteraction, IController> entry : interactionControllers.entrySet()){
            IInteraction interaction = entry.getKey();
            IController interactionController = entry.getValue();

            if (interaction.isFinished()) {
                finishedInteractions.add(interaction);
            }
            else {
                interactionController.update(delta);
            }
        }
        for (IInteraction finishedInteraction : finishedInteractions) {
            interactionControllers.remove(finishedInteraction);
            adlez.removeInteractionFromWorld(finishedInteraction);
        }
    }

    private void checkIfPlayerDied() {
        if (player.getHealth() == 0) {
            GameSound dyingSound = new LibGDXSoundAdapter(AssetStrings.TEMP_DYING_SOUND);
            dyingSound.play(0.5f);
            ScreenManager screenManager = ScreenManager.getInstance();
            screenManager.showScreen(ScreenEnum.GAME_OVER);
        }
    }
    
    private void renderWorldObjects() {
    
        // Render player
        playerController.render(batch);
    
        //Render enemies
        for(Map.Entry<IEnemy, ICharacterController> entry : enemies.entrySet()) {
            IController enemyController = entry.getValue();
            enemyController.render(batch);
        }
    
        // Render stationary world objects
        obstaclesController.render(batch);
        chestsController.render(batch);
        friendlyNPCController.render(batch);
        areaConnectionController.render(batch);
        manaFountainController.render(batch);
    }

    private void debugRender(){
        debugRenderer.setProjectionMatrix(playerCam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(1, 1, 0, 1);
        HitBox attackHitBox = PlayerController.currentAttack.getHitBox();
        HitBox interactionHitBox = PlayerController.currentInteraction.getHitBox();
        debugRenderer.rect(attackHitBox.getX(), attackHitBox.getY(), attackHitBox.getWidth(), attackHitBox.getHeight());
        debugRenderer.rect(interactionHitBox.getX(), interactionHitBox.getY(), 
                interactionHitBox.getWidth(), interactionHitBox.getHeight());
        debugRenderer.rect(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        List <IWall> tempList = adlez.getWalls();
        for(IWall wall : tempList){
            debugRenderer.rect(wall.getPosX(), wall.getPosY(), wall.getWidth(), wall.getHeight());
        }
        debugRenderer.end();
    }

    @Override
    public void gateOpen() {
        if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_1) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea2());
        } else if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_2) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea1());
        }
    }

    @Override
    public void shopOpen(NPCShop shop) {
        // Should open the shop view...
        // Waiting for the inventory view to be implemented...
    }
}
