package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.obstacles.IChest;

import java.util.List;

/**
 * Created by martinso on 01/05/16.
 */
public class ChestView {

    private Texture closedChestTexture;
    private Texture openChestTexture;

    public ChestView(String closedChestImg, String openChestImg) {
        closedChestTexture = new Texture((Gdx.files.internal((closedChestImg))));
        openChestTexture = new Texture((Gdx.files.internal((openChestImg))));
    }

    public void generateChests(List<IChest> chests, SpriteBatch batch) {
        for(IChest ch : chests) {
            if(ch.isOpened()){
                batch.draw(openChestTexture, ch.getPosX(), ch.getPosY());
            }else{
                batch.draw(closedChestTexture, ch.getPosX(), ch.getPosY());
            }
        }
    }
}
