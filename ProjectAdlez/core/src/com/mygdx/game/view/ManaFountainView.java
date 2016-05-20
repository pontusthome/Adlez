package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.obstacles.IManaFountain;

import java.util.List;

/**
 * Created by martinso on 15/05/16.
 */
public class ManaFountainView {
    private Texture manaFountainTexture;

    public ManaFountainView(String manaFountianImg) {
        manaFountainTexture = new Texture((Gdx.files.internal((manaFountianImg))));
    }

    public void generateManaFountains(List<IManaFountain> manaFountains, SpriteBatch batch) {
        for(IManaFountain fountains : manaFountains) {
            batch.draw(manaFountainTexture, fountains.getPosX(), fountains.getPosY());
        }
    }
}
