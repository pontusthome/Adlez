package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.controller.EnemyController;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viktor on 2016-04-19.
 */
public class GameScreen extends AbstractScreen {

    private Adlez adlez = Adlez.getInstance();

    private Player player = adlez.getPlayer();
    private PlayerController playerController;
    private OrthographicCamera playerCam;

    private HashMap<NPC, EnemyController> enemies;

    public SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
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
        playerController = new PlayerController(player, "playerSpritesMove.png");

        // Spawning enemies.
        enemies = new HashMap<NPC, EnemyController>();
        for (NPC enemy: adlez.getEnemies()) {
            EnemyController enemyController = new EnemyController(enemy, "playerSpritesMove.png", player);
            enemies.put(enemy, enemyController);
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
        playerCam.position.set(player.getPosX() + (playerController.getCurrentFrame().getRegionWidth() / 2),
                player.getPosY() + (playerController.getCurrentFrame().getRegionHeight() / 2),
                0); // z = 0, non 3D
        batch.draw(playerController.getCurrentFrame(),
                player.getPosX(),
                player.getPosY());

        // Updating enemies
        for(Map.Entry<NPC, EnemyController> entry : enemies.entrySet()) {
            NPC enemy = entry.getKey();
            EnemyController enemyController = entry.getValue();

            enemyController.update();
            batch.draw(enemyController.getCurrentFrame(),
                    enemy.getPosX(),
                    enemy.getPosY());
        }
        batch.end();
    }
}