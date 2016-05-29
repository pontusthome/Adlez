package com.mygdx.game.screens;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Viktor on 2016-05-24.
 */
public class StatusUI extends Window{

    private Image hpBar;
    private Image mpBar;

    private ImageButton inventoryButton;

    private int currentGoldValue = 0;
    private int currentHpValue = 0;
    private int currentMpValue = 0;

    private int maxHP = 100;
    private int maxMP = 100;

    private Label currentHpLabel;
    private Label currentMpLabel;
    private Label currentGoldLabel;

    private Label healthLabel;
    private Label manaLabel;
    private Label goldLabel;

    private float barWidth = 0;
    private float barHeight = 0;

    private IPlayer player;

    private boolean openInventory;


    public StatusUI(IPlayer player) {
        super("Player Status",AssetStrings.STATUSUI_SKIN);

        openInventory = false;

        this.player = player;
        WidgetGroup group = new WidgetGroup();
        WidgetGroup group2 = new WidgetGroup();

        hpBar = new Image(AssetStrings.STATUSUI_TEXTUREATLAS.findRegion("HP_Bar"));
        Image bar = new Image(AssetStrings.STATUSUI_TEXTUREATLAS.findRegion("Bar"));
        mpBar = new Image(AssetStrings.STATUSUI_TEXTUREATLAS.findRegion("MP_Bar"));
        Image bar2 = new Image(AssetStrings.STATUSUI_TEXTUREATLAS.findRegion("Bar"));

        barWidth = hpBar.getWidth();
        barHeight = hpBar.getHeight();

        healthLabel = new Label("Health: ", AssetStrings.STATUSUI_SKIN);
        currentHpLabel = new Label(String.valueOf(currentHpValue), AssetStrings.STATUSUI_SKIN);
        manaLabel = new Label("Mana: ", AssetStrings.STATUSUI_SKIN);
        currentMpLabel = new Label(String.valueOf(currentMpValue), AssetStrings.STATUSUI_SKIN);
        goldLabel = new Label("Gold: ", AssetStrings.STATUSUI_SKIN);
        currentGoldLabel = new Label(String.valueOf(currentGoldValue), AssetStrings.STATUSUI_SKIN);

        inventoryButton = new ImageButton(AssetStrings.STATUSUI_SKIN, "inventory-button");

        //Align images
        hpBar.setPosition(3, 6);
        mpBar.setPosition(3, 6);

        //add to widget groups
        group.addActor(bar);
        group.addActor(hpBar);
        group2.addActor(bar2);
        group2.addActor(mpBar);

        defaults().expand().fill();

        this.pad(this.getPadTop() + 10, 10, 10, 10);
        this.add();
        this.add(inventoryButton).align(Align.right);
        this.row();

        this.add(group).size(bar.getWidth(), bar.getHeight()).padRight(10);
        this.add(healthLabel);
        this.add(currentHpLabel).align(Align.left);
        this.row();

        this.add(group2).size(bar.getWidth(), bar.getHeight()).padRight(10);
        this.add(manaLabel);
        this.add(currentMpLabel).align(Align.left);
        this.row();

        this.add(goldLabel);
        this.add(currentGoldLabel).align(Align.left);
        this.row();

        this.pack();

        update();

    }

    public void update(){
        currentGoldValue = player.getGold();
        currentGoldLabel.setText(String.valueOf(currentGoldValue));

        currentHpValue = player.getHealth();
        maxHP = player.getMaxHealth();
        currentHpLabel.setText(String.valueOf(currentHpValue));
        updateBar(hpBar,currentHpValue,maxHP);

        currentMpValue = player.getMana();
        maxMP = player.getMaxMana();
        currentMpLabel.setText(String.valueOf(currentMpValue));
        updateBar(mpBar,currentMpValue,maxMP);
    }

    public void updateBar(Image bar, int currentVal, int maxVal){
        int val = MathUtils.clamp(currentVal, 0, maxVal);
        float tempPercent = (float) val / (float) maxVal;
        float percentage = MathUtils.clamp(tempPercent, 0, 100);
        bar.setSize(barWidth*percentage, barHeight);
    }
}
