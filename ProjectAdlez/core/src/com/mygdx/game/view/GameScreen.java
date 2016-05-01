package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import com.mygdx.game.controller.IController;
import com.mygdx.game.controller.CombatHandler;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.controller.EnemyController;
import com.mygdx.game.model.*;
import com.mygdx.game.model.handler.CollisionHandler2;
import com.mygdx.game.utils.AssetStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor on 2016-04-19.
 */
public class GameScreen extends AbstractScreen {

    private Adlez adlez = Adlez.getInstance();

    private IPlayer player = adlez.getPlayer();
    private IController playerController;
    private OrthographicCamera playerCam;

    private HashMap<INPC, IController> enemies;

    private SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
    private TiledMap tileMap;

    private ObstaclesView obstaclesView;
    private ChestView chestView;
    
    private CollisionHandler2 collisionHandler;
    
    private static final float UNIT_SCALE = 1/2f;
    private static final float WIDTH_SCALE = 2/3f;
    private static final float HEIGHT_SCALE = 2/3f;

    public GameScreen() {
        super();
        batch = new SpriteBatch();
        obstaclesView = new ObstaclesView(batch, AssetStrings.BOX_OBSTACLE_IMAGE);
        chestView = new ChestView(batch, AssetStrings.CHEST_IMAGE);

    }

    @Override
    public void buildStage() {
    
        collisionHandler = adlez.getCollisionHandler();
        
        // Creating camera
        playerCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        // Spawning player.
        playerController = new PlayerController(player, AssetStrings.MOVE_SPRITES_IMAGE);

        // Spawning enemies.
        enemies = new HashMap<INPC, IController>();
        for (IEnemy enemy: adlez.getEnemies()) {
            EnemyController enemyController = new EnemyController((INPC) enemy, AssetStrings.MOVE_SPRITES_IMAGE, player);
            enemies.put((INPC) enemy, enemyController);
        }

        // temporary things, just testing
        tileMap = new TmxMapLoader().load(AssetStrings.TEST_LEVEL_TMX);
        float unitScale = UNIT_SCALE;

        renderer = new OrthoCachedTiledMapRenderer(tileMap, unitScale);
        playerCam.setToOrtho(false, Gdx.graphics.getWidth() * WIDTH_SCALE, 
                Gdx.graphics.getHeight() * HEIGHT_SCALE);
    }

    @Override
    public void render(float delta) {
        updateGame();

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

        // Render player
        playerController.render(batch);
        
        //Render enemies
        for(Map.Entry<INPC, IController> entry : enemies.entrySet()) {
            IController enemyController = entry.getValue();
            enemyController.render(batch);
        }
        
        // Generate obstacles
        obstaclesView.generateObstacles();
        chestView.generateChests();

        batch.end();

        // Render shapes for debugging purposes
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(playerCam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(CombatHandler.playerWeaponHitbox.getX(), CombatHandler.playerWeaponHitbox.getY(), CombatHandler.playerWeaponHitbox.getWidth(), CombatHandler.playerWeaponHitbox.getHeight());
        shapeRenderer.rect(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
    
        List <IWall> listt = adlez.getWalls();
        for(IWall wall : listt){
            shapeRenderer.rect(wall.getPosX(), wall.getPosY(), wall.getWidth(), wall.getHeight());
        }
        shapeRenderer.end();
    }

    public void updateGame() {
        // Updating player
        playerController.update();
        collisionHandler.updatePlayer();

        // Updating enemies
        List<INPC> killedEnemies = new ArrayList<INPC>();
        for(Map.Entry<INPC, IController> entry : enemies.entrySet()) {
            INPC enemy = entry.getKey();
            IController enemyController = entry.getValue();

            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
            }
            else {
                enemyController.update();
            }
        }
        for (INPC deadEnemy: killedEnemies) {
            enemies.remove(deadEnemy);
        }
        
        collisionHandler.update();
    }
}
