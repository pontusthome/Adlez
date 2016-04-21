package com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.EnemyController;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayScreen implements Screen {

    private Adlez adlez = Adlez.getInstance();
    private Player player = adlez.getPlayer();
    private List<NPC> enemies = adlez.getEnemies();

    private CharacterView playerView;
    private PlayerController playerController;

    private List<CharacterView> enemyViews;
    private EnemyController enemyController;

    private Game game;

    private OrthographicCamera playerCam;

    private SpriteBatch batch;

    private TiledMap testing1;
    private OrthoCachedTiledMapRenderer renderer;
    private Stage stage;

    public PlayScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        playerCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        // Spawning player.
        playerView = new CharacterView("playerSpritesMove.png");
        playerController = new PlayerController(player);

        // Spawning enemies.
        enemyController = new EnemyController(enemies, player);
        enemyViews = new ArrayList<CharacterView>();
        for (int i = 0; i < enemies.size(); i++) {
            CharacterView enemyView = new CharacterView("playerSpritesMove.png");
            enemyViews.add(enemyView);
        }

        // temporary things, just testing
        testing1 = new TmxMapLoader().load("test1.tmx");
        renderer = new OrthoCachedTiledMapRenderer(testing1);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render Tiled map
        renderer.setView(playerCam);
        renderer.render();
        stage.act();

        stage.draw();
        batch.setProjectionMatrix((playerCam.combined));
        playerCam.update();

        batch.begin();

        // Updating player
        playerController.update();
        playerView.update(player.getDirection());
        playerCam.position.set(player.getPosX() + (playerView.getCurrentFrame().getRegionWidth() / 2),
                               player.getPosY() + (playerView.getCurrentFrame().getRegionHeight() / 2),
                               0); // z = 0, non 3D
        batch.draw(playerView.getCurrentFrame(),
                   player.getPosX(),
                   player.getPosY());

        // Updating enemies
        enemyController.update();
        for (int i = 0; i < enemyViews.size(); i++) {
            CharacterView enemyView = enemyViews.get(i);
            NPC enemy = enemies.get(i);

            enemyView.update(enemy.getDirection());
            batch.draw(enemyView.getCurrentFrame(),
                    enemy.getPosX(),
                    enemy.getPosY());
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
