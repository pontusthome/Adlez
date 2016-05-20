package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Viktor on 2016-05-06.
 */
public class ManaBar implements IStatusBar {
    private ICharacter character;
    private Pixmap background;
    private Pixmap bar;

    private int barWidth;
    private int barHeight;

    private int currentValue;
    private int maxValue;
    private Image barImage;

    public ManaBar(ICharacter character) {
        this.character = character;
        updateCurrentValues();
        this.bar = new Pixmap(currentValue, 10, Pixmap.Format.RGBA8888);
        bar.setColor(Color.BLUE);
        bar.fill();

        this.background = new Pixmap(100, 10, Pixmap.Format.RGBA8888);
        background.setColor(Color.WHITE);
        background.fill();
        updateBar();
    }

    public void updateBar() {
        updateCurrentValues();
        background.drawPixmap(bar, 0, 0, 0, 0, currentValue, 9);
        barImage = new Image(new Texture(background));
    }

    private void updateCurrentValues() {
        maxValue = 100;
        currentValue = 100;
        /*this.maxValue = character.getMaxMana();
        if (character.getMaxMana() == 0) {
            this.currentValue = 100;
        } else {
            this.currentValue = character.getMana() * 100 / character.getMaxMana();
        }*/
    }

    public Image getBarImage() {
        return barImage;
    }
}