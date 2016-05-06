package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.IAreaConnection;

import java.util.List;

/**
 * Created by martinso on 06/05/16.
 */
public class GateView {

    private Texture gateTexture;

    public GateView(String gateImg) {
        gateTexture = new Texture((Gdx.files.internal((gateImg))));
    }

    public void generateGate(List<IAreaConnection> ac, SpriteBatch batch) {
        for(IAreaConnection a : ac) {
            batch.draw(gateTexture, a.getPosX(), a.getPosY());
        }
    }
}
