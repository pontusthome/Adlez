package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Pontus on 2016-04-29.
 */
public interface ICharacterView {
    void viewUpdate(int frame);
    TextureRegion getCurrentFrame();
    void setCurrentFrame(int frame);
    float getStateTime();
    void setStateTime(float stateTime);
    Animation getAnimation();
    void setAnimation(Animation animation);
}
