package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by martinso on 27/03/16.
 */
public class CharacterView implements ICharacterView {

<<<<<<< HEAD:ProjectAdlez/core/src/com/mygdx/game/controller/CharacterView.java
    protected Texture characterTexture;
    protected TextureRegion[] characterFrames;
    protected TextureRegion currentFrame;
    protected float stateTime;
    protected static final int col = 4;
    protected static final int row = 2;

    protected Animation animation;
=======
    private Texture characterTexture;
    private TextureRegion[] characterFrames;
    private TextureRegion currentFrame;
    private float stateTime;

    private Animation animation;
    private int col;
    private int row;
>>>>>>> origin/enemy_graphics:ProjectAdlez/core/src/com/mygdx/game/view/CharacterView.java

    public CharacterView(String characterImg, int col, int row) {
        this.col = col;
        this.row = row;

        characterTexture = new Texture((Gdx.files.internal((characterImg))));
        TextureRegion[][] tmp = TextureRegion.split(characterTexture,
                characterTexture.getWidth() / col,
                characterTexture.getHeight() / row);
        characterFrames = new TextureRegion[col * row];

        // Setting frames from player sheet.
        int index = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                characterFrames[index++] = tmp[j][i];
            }
        }
        // Sheet index:
        // 0 2 4 6
        // 1 3 5 7

        animation = new Animation(1, characterFrames);
        stateTime = 0f;
        currentFrame = animation.getKeyFrame(0);
    }

    public void viewUpdate(int frame) {
        setCurrentFrame(frame);

        if (getStateTime() < row) {
            setStateTime(getStateTime() + Gdx.graphics.getDeltaTime() * 6);
            if (getStateTime() > row) {
                setStateTime(0);
            }
        } else {
            setStateTime(0);
        }
        setStateTime(getStateTime() + Gdx.graphics.getDeltaTime());
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int frame) {
        this.currentFrame = getAnimation().getKeyFrame(frame + getStateTime());
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
