package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import com.mygdx.game.controller.CombatHandler;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.event.EnemyController;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.PlayerController;

/**
 * Created by Viktor on 2016-04-19.
 */
public class GameScreen extends AbstractScreen {

    private Adlez adlez = Adlez.getInstance();
    private Player player = adlez.getPlayer();

    private HashMap<NPC, CharacterView> enemies;
    private List<EnemyController> enemyControllers;

    private CharacterView playerView;

    public SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
    private PlayerController playerController;
    private OrthographicCamera playerCam;
    private TiledMap tileMap;


    public GameScreen(){
        super();
        batch = new SpriteBatch();
    }

    @Override
    public void buildStage() {

        playerCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        // Spawning player.
        playerView = new CharacterView("playerSpritesMove.png");
        playerController = new PlayerController(player, playerView);

        // Spawning enemies.
        enemies = new HashMap<NPC, CharacterView>();
        enemyControllers = new ArrayList<EnemyController>();
        for (NPC enemy: adlez.getEnemies()) {
            CharacterView enemyView = new CharacterView("playerSpritesMove.png");
            EnemyController enemyController = new EnemyController(enemy, enemyView, player);
            enemyControllers.add(enemyController);
            enemies.put(enemy, enemyView);
        }
        // temporary things, just testing
        tileMap = new TmxMapLoader().load("test1.tmx");
        float unitScale = 1/3f;
        renderer = new OrthoCachedTiledMapRenderer(tileMap,unitScale);
        playerCam.setToOrtho(false,Gdx.graphics.getWidth()*2/3,Gdx.graphics.getHeight()*2/3);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render Tiled map
        renderer.setView(playerCam);
        renderer.render();
        act();

        draw();
        batch.setProjectionMatrix((playerCam.combined));
        playerCam.update();

        batch.begin();

        // Updating player
        playerController.update();
        playerCam.position.set(player.getPosX() + (playerView.getCurrentFrame().getRegionWidth() / 2),
                player.getPosY() + (playerView.getCurrentFrame().getRegionHeight() / 2),
                0); // z = 0, non 3D
        batch.draw(playerView.getCurrentFrame(),
                player.getPosX(),
                player.getPosY());
                
        /** Needed at least temporarily to exclude EnemyControllers of enemies that got killed by a player attack. 
         * isEnemyKilled() returns true if at least one enemy has been killed */
        if(CombatHandler.isEnemyKilled()){
            List<EnemyController> enemyControllersToKeep = new ArrayList<>();
            for(EnemyController enemyController: enemyControllers){
                if(enemyController.isAlive()){
                    enemyControllersToKeep.add(enemyController);
                }else{
                    enemies.remove(enemyController.getEnemy());
                }
            }
            enemyControllers = enemyControllersToKeep;
            
            /** Verifies for CombatHandler that necessary actions have been taken after enemies have gotten killed */
            CombatHandler.clearIsEnemyKilled();
        }

        // Updating enemies
        for (EnemyController enemyController: enemyControllers) {
            enemyController.update();
        }
        for(Map.Entry<NPC, CharacterView> entry : enemies.entrySet()) {
            NPC enemy = entry.getKey();
            CharacterView view = entry.getValue();
            batch.draw(view.getCurrentFrame(),
                    enemy.getPosX(),
                    enemy.getPosY());
        }
        batch.end();
    }
}