package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Viktor on 2016-05-29.
 */
public class InstructionsScreen extends AbstractScreen {

    private Texture backgroundTexture;

    public InstructionsScreen(){
        super();
        backgroundTexture = new Texture( Gdx.files.internal(AssetStrings.INSTRUCTIONS_BACKGROUND_IMAGE) );
    }

    @Override
    public void buildStage() {
        ImageButton button = new ImageButton(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        button.setSize(1280,720);
        addActor(button);
        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x,
                                     float y, int pointer, int button) {
                newGame();
                initiateGame();
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
