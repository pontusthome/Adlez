package com.mygdx.game.model;

import java.util.List;

/**
 * Created by Michel on 2016-04-19.
 */
public class Player extends Character {
    private int experience;

    //Change String class to IItem/Item later
    private List<Item> swordEquipped;
    private List<Item> armorEquipped;
    private Weapon weapon;
    private Armor armor;

    public void equipItem(Item item) {
        if (item.equals(weapon)) {
            swordEquipped.add(item);
            setAttackDamage(item.getStats());
        } else if(item.equals(armor)) {
            armorEquipped.add(item);
            setMaxHealth(item.getStats());
        }
    }
    
    public List<Item> getItems(Item item) {
        if (item.equals(weapon)) {
            return swordEquipped;
        } else if(item.equals(armor)) {
            return armorEquipped;
        }
        return null;
    }

}
