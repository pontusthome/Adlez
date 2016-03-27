package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by martinso on 27/03/16.
 */
public class CharacterView {

    private Vector2 characterPosition;
    private String characterImg;

    private Texture characterTexture;
    private TextureRegion[] characterFrames;
    private TextureRegion currentFrame;
    private float stateTime;
    private static final int col = 4;
    private static final int row = 2;

    private Animation animation;

    public CharacterView(Vector2 position, String characterImg) {
        this.characterPosition = position;
        this.characterImg = characterImg;

        characterTexture = new Texture((Gdx.files.internal((characterImg))));
        TextureRegion[][] tmp = TextureRegion.split(characterTexture, characterTexture.getWidth() / col, characterTexture.getHeight() / row);
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

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public Vector2 getCharacterPosition() {
        return characterPosition;
    }

    public void setCharacterPositionX(float characterPosition) {
        this.characterPosition.x = characterPosition;
    }

    public void setCharacterPositionY(float characterPosition) {
        this.characterPosition.y = characterPosition;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
