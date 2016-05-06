package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IChest;
import com.mygdx.game.model.IObstacle;

import java.util.List;

/**
 * Created by martinso on 01/05/16.
 */
public class ChestView {

    private Texture chestTexture;

    public ChestView(String chestImg) {
        chestTexture = new Texture((Gdx.files.internal((chestImg))));
    }

    public void generateChests(List<IChest> chests, SpriteBatch batch) {
        for(IChest ch : chests) {
            batch.draw(chestTexture, ch.getPosX(), ch.getPosY());
        }
    }
}
