package com.mygdx.game.model.characters;

import com.mygdx.game.model.actions.IInteraction;
import com.mygdx.game.model.core.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 1.5.2016.
 */

public class FriendlyNPC extends NPC implements IFriendlyNPC {

    private NPCShop shop;
    private List<ShopOpenListener> listeners = new ArrayList<>();

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

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof IInteraction) {
            IInteraction interaction = (IInteraction) other;
            if (interaction.byPlayer()) {
                notifyListeners();
            }
        }
    }

    @Override
    public void add(ShopOpenListener listener) {
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    @Override
    public void remove(ShopOpenListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (ShopOpenListener listener : listeners) {
            listener.shopOpen(shop);
        }
    }
}
