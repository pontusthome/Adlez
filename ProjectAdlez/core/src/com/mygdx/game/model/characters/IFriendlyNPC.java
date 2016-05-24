package com.mygdx.game.model.characters;

/**
 * Created by Michel on 1.5.2016.
 */
public interface IFriendlyNPC extends INPC {
    void createShop(NPCShop shop);
    NPCShop getShop();
    void add(ShopOpenListener listener);
    void remove(ShopOpenListener listener);
}
