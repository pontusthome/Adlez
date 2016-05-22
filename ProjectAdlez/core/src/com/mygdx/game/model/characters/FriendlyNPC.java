package com.mygdx.game.model.characters;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.ShopOpenListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 1.5.2016.
 */

public class FriendlyNPC extends NPC implements IFriendlyNPC {

    private Adlez adlez = Adlez.getInstance();
    private IPlayer player = adlez.getPlayer();
    private NPCShop shop;
    private List<ShopOpenListener> listeners = new ArrayList<ShopOpenListener>();

    public FriendlyNPC(int direction, float speed, int width,
                       int height, float posX, float posY,
                       int maxHealth, int attackDamage,
                       int gold, int mana) {

        super(direction, speed, width, height,
                posX, posY, maxHealth, attackDamage,
                gold, mana);
    }

    @Override
    public void createShop(NPCShop shop) {
        this.shop = shop;
    }

    @Override
    public NPCShop getShop() {
        return shop;
    }

    /**
     * Small fee of 5 gold to open the shop.
     */
    @Override
    public void onCollide(Collidable other) {
        if(player.getGold() >= 5) {
            player.setGold(player.getGold() - 5);
            if (other instanceof IInteraction) {
                for (ShopOpenListener listener : listeners) {
                    listener.shopOpen(shop);
                }
            }
        }
    }

    @Override
    public void add(ShopOpenListener listener) {
        listeners.add(listener);
    }

    @Override
    public void remove(ShopOpenListener listener) {
        listeners.remove(listener);
    }

}
