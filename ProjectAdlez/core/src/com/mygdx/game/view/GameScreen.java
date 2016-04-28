package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import com.mygdx.game.controller.CombatHandler;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.controller.EnemyController;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Obstacles;
import com.mygdx.game.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
    private TiledMap tileMap;

    private ObstaclesView obstaclesView;

    public GameScreen() {
        super();
        batch = new SpriteBatch();
        obstaclesView = new ObstaclesView(batch, "boxObstacle.jpeg");
    }

    @Override
    public void buildStage() {

        playerCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        // Spawning player.
        playerController = new PlayerController(player, "moveSpriteV2.png");

        // Spawning enemies.
        enemies = new HashMap<NPC, EnemyController>();
        for (NPC enemy: adlez.getEnemies()) {
            EnemyController enemyController = new EnemyController(enemy, "moveSpriteV2.png", player);
            enemies.put(enemy, enemyController);
        }

        // temporary things, just testing
        tileMap = new TmxMapLoader().load("testLevel1.tmx");
        float unitScale = 1 / 2f;

        renderer = new OrthoCachedTiledMapRenderer(tileMap, unitScale);
        playerCam.setToOrtho(false, Gdx.graphics.getWidth() * 2 / 3, Gdx.graphics.getHeight() * 2 / 3);
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

        // Updating player and render. Send batch
        playerController.update();
        playerCam.position.set(player.getPosX() + (playerController.getCurrentFrame().getRegionWidth() / 2),
                player.getPosY() + (playerController.getCurrentFrame().getRegionHeight() / 2),
                0); // z = 0, non 3D
        batch.draw(playerController.getCurrentFrame(),
                player.getPosX(),
                player.getPosY());

        // Updating enemies
        List<NPC> killedEnemies = new ArrayList<NPC>();
        for(Map.Entry<NPC, EnemyController> entry : enemies.entrySet()) {
            NPC enemy = entry.getKey();
            EnemyController enemyController = entry.getValue();

            if (!enemy.isAlive()) {
                killedEnemies.add(enemy);
            }
            else {
                enemyController.update();
                batch.draw(enemyController.getCurrentFrame(),
                        enemy.getPosX(),
                        enemy.getPosY());
            }
        }
        for (NPC deadEnemy: killedEnemies) {
            enemies.remove(deadEnemy);
        }
        // Generate obstacles
        obstaclesView.generateObstacles();

        batch.end();

        // For debugging attack hitbox
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(playerCam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(CombatHandler.playerWeaponHitbox.getX(), CombatHandler.playerWeaponHitbox.getY(), CombatHandler.playerWeaponHitbox.getWidth(), CombatHandler.playerWeaponHitbox.getHeight());
        shapeRenderer.rect(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        shapeRenderer.end();
    }
}