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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.PlayerController;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayScreen implements Screen {

    private Game game;

    private CharacterView characterView;
    private PlayerController playerController;

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


        // Spawning player. Temporary position...
        characterView = new CharacterView(new Vector2(100, 100), "playerSpritesMove.png");
        playerController = new PlayerController(characterView);

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

        playerCam.position.set(characterView.getCharacterPosition().x + (characterView.getCurrentFrame().getRegionWidth() / 2), characterView.getCharacterPosition().y + (characterView.getCurrentFrame().getRegionHeight() / 2), 0); // z = 0, non 3D
        stage.draw();
        batch.setProjectionMatrix((playerCam.combined));
        playerCam.update();

        batch.begin();

        batch.draw(characterView.getCurrentFrame(), characterView.getCharacterPosition().x, characterView.getCharacterPosition().y);

        // Updating playerController
        playerController.updatePlayer();

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
