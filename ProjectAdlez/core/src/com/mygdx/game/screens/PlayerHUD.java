package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.IPlayer;

/**
 * Created by Viktor on 2016-04-26.
 */
public class PlayerHUD implements Screen{

    private ICharacter player;
    private Stage stage;
    private Viewport viewport;

    private StatusUI statusUI;
    private InventoryUI inventoryUI;


    public PlayerHUD(OrthographicCamera hudCamera, IPlayer player) {

        this.viewport = new ScreenViewport(hudCamera);
        this.stage = new Stage(viewport);
        this.player = player;

        this.statusUI = new StatusUI(player);
        this.inventoryUI = new InventoryUI();


        stage.addActor(inventoryUI);

        statusUI.setVisible(true);
        statusUI.setPosition(0, 0);
        statusUI.setKeepWithinStage(false);
        statusUI.setMovable(false);
        stage.addActor(statusUI);
        statusUI.validate();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
        stage.dispose();

    }

    public void update(){
        statusUI.update();
    }

    public Stage getStage() {
        return stage;
    }
}