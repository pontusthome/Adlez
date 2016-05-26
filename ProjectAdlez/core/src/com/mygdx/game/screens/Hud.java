package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.view.IStatusBar;

/**
 * Created by Viktor on 2016-04-26.
 */
public class HUD implements Screen{

    private Adlez adlez = Adlez.getInstance();
    private GameScreen gameScreen;
    private ICharacter player;
    private OrthographicCamera hudCamera;
    private Stage stage;
    private Viewport viewport;

    private StatusUI statusUI;
    private InventoryUI inventoryUI;


    private Label healthLabel;
    private Label manaLabel;
    private Label experienceLabel;
    private Label goldLabel;

    private IStatusBar playerHealthBar;
    private IStatusBar playerManaBar;
    private IStatusBar playerExperienceBar;


    public HUD(OrthographicCamera hudCamera, IPlayer player) {

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