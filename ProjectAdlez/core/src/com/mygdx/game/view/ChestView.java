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
    private Adlez adlez = Adlez.getInstance();
    private SpriteBatch spriteBatch;
    private List<IChest> chests = adlez.getChests();

    public ChestView(SpriteBatch spriteBatch, String chestImg) {
        this.spriteBatch = spriteBatch;
        chestTexture = new Texture((Gdx.files.internal((chestImg))));
    }

    public void generateChests() {
        for(IChest ch : chests) {
            spriteBatch.draw(chestTexture, ch.getPosX(), ch.getPosY());
        }
    }
}
