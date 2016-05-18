package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.builder.AreaBuilder;
import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.builder.AreaIO;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Viktor on 2016-04-19.
 */
public class MainMenuScreen extends AbstractScreen {

    private Texture backGroundTexture;
    private Texture newGameButtonTexture;
    private Texture loadGameButtonTexture;
    private Texture exitGameButtonTexture;

    public MainMenuScreen() {
        super();
        backGroundTexture = new Texture(Gdx.files.internal(AssetStrings.MAIN_MENU_BACKGROUND_IMAGE));
        newGameButtonTexture = new Texture(Gdx.files.internal(AssetStrings.NEW_GAME_BUTTON_IMAGE));
        loadGameButtonTexture = new Texture(Gdx.files.internal(AssetStrings.LOAD_GAME_BUTTON_IMAGE));
        exitGameButtonTexture = new Texture(Gdx.files.internal(AssetStrings.EXIT_GAME_BUTTON_IMAGE));
    }

    @Override
    public void buildStage() {
        Image backGround = new Image(backGroundTexture);
        addActor(backGround);

        ImageButton newGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(newGameButtonTexture)));
        newGameButton.setPosition(getWidth()/2, getHeight()/4*3, Align.center);
        addActor(newGameButton);

        ImageButton loadGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(loadGameButtonTexture)));
        loadGameButton.setPosition(getWidth()/2, getHeight()/4*2, Align.center);
        addActor(loadGameButton);

        ImageButton exitGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exitGameButtonTexture)));
        exitGameButton.setPosition(getWidth()/2, getHeight()/4*1, Align.center);
        addActor(exitGameButton);


        newGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {
                // Initiate the area
                Adlez adlez = Adlez.getInstance();
                AreaHandler areaHandler = AreaHandler.getInstance();
                adlez.initiateArea(areaHandler.getCurrentArea());

                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
                return false;
            }
        });

        loadGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {

                // Load the AreaHandler
                AreaIO areaBuilder = new AreaBuilder();
                AreaHandler areaHandler = areaBuilder.loadAreaHandler();

                // Load the Player
                try {
                    areaBuilder.loadPlayer();
                } catch (ItemNotFoundException e) {
                    e.getMessage();
                }

                // Initiate game
                Adlez adlez = Adlez.getInstance();
                adlez.initiateArea(areaHandler.getCurrentArea());

                ScreenManager.getInstance().showScreen(ScreenEnum.GAME);
                return false;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        backGroundTexture.dispose();
        newGameButtonTexture.dispose();
        loadGameButtonTexture.dispose();
        exitGameButtonTexture.dispose();
    }
}

