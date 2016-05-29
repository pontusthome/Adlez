package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.items.IItem;
import com.mygdx.game.utils.AssetStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor on 2016-05-24.
 */
public class InventoryUI extends Window {

    private IPlayer player;
    private List<IItem> playerInventory;
    private List<ImageButton> inventorySlot;
    private static final int INVENTORY_MAX_SIZE = 16;
    private boolean inventoryChanged;

    public InventoryUI(IPlayer player) {
        super("Inventory", AssetStrings.STATUSUI_SKIN);
        this.player = player;
        this.playerInventory = player.getInventory();
        inventorySlot =  new ArrayList<>(INVENTORY_MAX_SIZE);

        inventoryChanged = false;


        setSize(400,400);

        for(int i = 0; i < INVENTORY_MAX_SIZE; i++){
            ImageButton tmp = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.INVENTORY_SLOI)))));
            if(!playerInventory.isEmpty()){
                if(playerInventory.get(i) != null){
                    String tmpString = playerInventory.get(i).getName();
                    tmp = new ImageButton(new TextureRegionDrawable(AssetStrings.ITEMS_TEXTUREATLAS.findRegion(tmpString)));
                }
            }
            inventorySlot.add(tmp);
            add(inventorySlot.get(i));

            if(i == 3 || i == 7 || i == 11){
                row();
            }
        }

        update();
    }

    public void update(){
            for (int i = 0; i < INVENTORY_MAX_SIZE; i++) {
                ImageButton tmp = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(AssetStrings.INVENTORY_SLOI)))));
                if (!playerInventory.isEmpty()) {
                    if (playerInventory.get(i) != null) {
                        String tmpString = playerInventory.get(i).getName();
                        tmp = new ImageButton(new TextureRegionDrawable(AssetStrings.ITEMS_TEXTUREATLAS.findRegion(tmpString)));
                    }
                }
                inventorySlot.set(i, tmp);

            }



    }

}
