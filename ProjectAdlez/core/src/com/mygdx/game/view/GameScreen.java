package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.PlayerController;

/**
 * Created by Viktor on 2016-04-19.
 */
public class GameScreen extends AbstractScreen {

    public SpriteBatch batch;
    private OrthoCachedTiledMapRenderer renderer;
    private CharacterView characterView;
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

        characterView = new CharacterView(new Vector2(100, 100), "playerSpritesMove.png");
        playerController = new PlayerController(characterView);

        // temporary things, just testing
        tileMap = new TmxMapLoader().load("test1.tmx");
        float unitScale = 1/3f;
        renderer = new OrthoCachedTiledMapRenderer(tileMap,unitScale);
        playerCam.setToOrtho(false,200,200);

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

        playerCam.position.set(characterView.getCharacterPosition().x + (characterView.getCurrentFrame().getRegionWidth() / 2), characterView.getCharacterPosition().y + (characterView.getCurrentFrame().getRegionHeight() / 2), 0); // z = 0, non 3D
        draw();
        batch.setProjectionMatrix((playerCam.combined));
        playerCam.update();

        batch.begin();

        batch.draw(characterView.getCurrentFrame(), characterView.getCharacterPosition().x, characterView.getCharacterPosition().y);

        // Updating playerController
        playerController.updatePlayer();

        batch.end();
    }
}
