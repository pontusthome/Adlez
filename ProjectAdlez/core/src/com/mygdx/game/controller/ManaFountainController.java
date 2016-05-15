package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IManaFountain;
import com.mygdx.game.model.ManaFountain;
import com.mygdx.game.view.ManaFountainView;

import java.util.List;

/**
 * Created by martinso on 15/05/16.
 */
public class ManaFountainController implements IController {

    private Adlez adlez;
    private ManaFountainView manaFountainView;
    private List<IManaFountain> manaFountains;

    public ManaFountainController(List<IManaFountain> manaFountains, String manaFountainImg) {
        adlez = Adlez.getInstance();
        this.manaFountains = manaFountains;
        manaFountainView = new ManaFountainView(manaFountainImg);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {
        manaFountainView.generateManaFountains(manaFountains, batch);
    }
}
