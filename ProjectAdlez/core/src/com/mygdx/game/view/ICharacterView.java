package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface ICharacterView {
    public void viewUpdate(int frame);
    public TextureRegion getCurrentFrame();
    public void setCurrentFrame(int frame);
    public float getStateTime();
    public void setStateTime(float stateTime);
    public Animation getAnimation();
    public void setAnimation(Animation animation);
}
