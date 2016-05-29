package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.controller.ICharacterController;
import com.mygdx.game.controller.IMenuController;
import com.mygdx.game.model.characters.IPlayer;

/**
 * Created by Viktor on 2016-04-26.
 */
public class PlayerHUD implements Screen{

    private IPlayer player;
    private IMenuController playerController;
    private Stage stage;
    private Viewport viewport;

    private StatusUI statusUI;
    private InventoryUI inventoryUI;
    private InGameMenuUI gameMenuUI;

    private ImageButton inventoryButton;


    public PlayerHUD(OrthographicCamera hudCamera, IPlayer player, ICharacterController playerController) {
        this.playerController = (IMenuController)playerController;
        this.viewport = new ScreenViewport(hudCamera);
        this.stage = new Stage(viewport);
        this.player = player;

        this.statusUI = new StatusUI(player);
        this.inventoryUI = new InventoryUI(player);
        this.gameMenuUI= new InGameMenuUI();


        gameMenuUI.setVisible(false);
        gameMenuUI.setPosition(400,200);
        gameMenuUI.setKeepWithinStage(true);
        gameMenuUI.setMovable(false);
        stage.addActor(gameMenuUI);
        gameMenuUI.validate();

        statusUI.setVisible(true);
        statusUI.setPosition(0,550);
        statusUI.setKeepWithinStage(true);
        statusUI.setMovable(true);
        stage.addActor(statusUI);
        statusUI.validate();

        inventoryUI.setVisible(false);
        inventoryUI.setPosition(0,0);
        inventoryUI.setKeepWithinStage(false);
        inventoryUI.setMovable(true);
        stage.addActor(inventoryUI);
        inventoryUI.validate();

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
        if(player.getIsInventoryChanged()){
            inventoryUI.update();
        }
        inventoryUI.setVisible(playerController.getInventoryOpen());
        gameMenuUI.setVisible(playerController.getGameMenuOpen());

    }

    public Stage getStage() {
        return stage;
    }
}