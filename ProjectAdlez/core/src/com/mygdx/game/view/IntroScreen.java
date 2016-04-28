package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Viktor on 2016-04-19.
 */
public class IntroScreen extends AbstractScreen {

    private Texture backgroundTexture;

    public IntroScreen(){
        super();
        backgroundTexture = new Texture( Gdx.files.internal(AssetStrings.INTRO_SCREEN_BACKGROUND_IMAGE) );
    }

    @Override
    public void buildStage() {
        ImageButton button = new ImageButton(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));

        //Image backGround = new Image(introBackground);
        addActor(button);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {
                ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                return false;
            }
        });
    }
    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
    }
}
