package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Viktor on 2016-05-29.
 */
public class InGameMenuUI extends Window {

    private ImageButton saveButton;
    private ImageButton loadButton;
    private ImageButton settingsButton;
    private ImageButton mainMenuButton;
    private ImageButton exitGameButton;


    public InGameMenuUI() {
        super("Game Menu",AssetStrings.STATUSUI_SKIN);

        saveButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.SAVE_GAME_BUTTON_IMAGE)))));
        loadButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.LOAD_GAME_BUTTON_IMAGE)))));
        settingsButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.SETTINGS_BUTTON_IMAGE)))));
        mainMenuButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.MAIN_MENU_BUTTON_IMAGE)))));
        exitGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.EXIT_GAME_BUTTON_IMAGE)))));

        //Not doing anything yet since the buttons is not connected to anything
        WidgetGroup group1 = new WidgetGroup();
        WidgetGroup group2 = new WidgetGroup();
        WidgetGroup group3 = new WidgetGroup();
        WidgetGroup group4 = new WidgetGroup();
        WidgetGroup group5 = new WidgetGroup();

        defaults().expand().fill();
        this.pad(this.getPadTop() + 10, 10, 10, 10);

        this.setSize(200,400);

        add(saveButton);
        row();
        add(loadButton);
        row();
        add(settingsButton);
        row();
        add(mainMenuButton);
        row();
        add(exitGameButton);

    }
}
