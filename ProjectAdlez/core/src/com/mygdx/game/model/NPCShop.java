package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InsufficientGoldException;

import java.util.List;

/**
 * Created by martinso on 10/05/16.
 */
public class NPCShop implements INPCShop {

    private Adlez adlez = Adlez.getInstance();
    private IPlayer player;
    private List<IItem> items;

    /**
     * Create a shop by putting items in constructor as a list.
     */
    public NPCShop(List<IItem> items) {
        this.items = items;
        player = adlez.getPlayer();
    }

    /**
     * The npc sells an item to the player.
     */
    public IItem sellItem(IItem item) throws InsufficientGoldException {
        int value = item.getGoldValue();
        if (value <= player.getGold()) {
            player.setGold(player.getGold() - value);
            return item;
        } else {
            throw new InsufficientGoldException("Insufficient Gold");
        }
    }

    /**
     * The npc buys the player's item and returns the value.
     * Assuming npc has infinite amount of gold.
     */
    public int buyItem(IItem item) {
        int value = item.getGoldValue();
        player.removeItem(item);
        return value;
    }

    public List<IItem> getItems() {
        return items;
    }


}
