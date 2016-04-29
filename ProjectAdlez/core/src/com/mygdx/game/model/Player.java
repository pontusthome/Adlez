package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michel on 2016-04-19.
 */
public class Player extends Character {
    private int experience;

    private Weapon weapon;
    private Armor armor;

    private IItem swordEquipped;
    private IItem armorEquipped;
    private boolean isWepSlotEmpty = true;
    private boolean isArmorSlotEmpty = true;
    private List<IItem> inventory;

    // This constructor should be used.
    public Player(int direction, float speed,
                  int width, int height,
                  float posX, float posY,
                  int maxHealth, int attackDamage, int gold, int mana) {
        setDirection(direction);
        setSpeed(speed);
        setWidth(width);
        setHeight(height);
        setPosX(posX);
        setPosY(posY);
        setOldYpos(posY);
        setOldXpos(posX);
        setMaxHealth(maxHealth);
        setHealth(maxHealth);
        setAttackDamage(attackDamage);
        setGold(gold);
        setMana(mana);
        // Size of inventory
        inventory = new ArrayList<IItem>(16);
    }

    @Override
    public void onCollide(WorldObject other) {
            setPosX(getOldXpos());
            setPosY(getOldYpos());
    }

    public void equipItem(IItem item) {
        if (item.equals(weapon)) {
            if(isWepSlotEmpty) {
                isWepSlotEmpty = false;
                swordEquipped = item;
                setAttackDamage(getAttackDamage() + item.getStats());
                removeItem(item);
            }
        } else if(item.equals(armor)) {
            if(isArmorSlotEmpty) {
                isArmorSlotEmpty = false;
                armorEquipped = item;
                setMaxHealth(getMaxHealth() + item.getStats());
                removeItem(item);
            }
        }
    }

    public void unEquipWeapon(IItem item) {
        if(!isWepSlotEmpty) {
            isWepSlotEmpty = true;
            swordEquipped = null;
            setAttackDamage(getAttackDamage() - item.getStats());
            lootItem(item);
        }
    }

    public void unEquipArmor(IItem item) {
        if(!isArmorSlotEmpty) {
            isArmorSlotEmpty = true;
            armorEquipped = null;
            setMaxHealth(getMaxHealth() - item.getStats());
            lootItem(item);
        }
    }

    public void lootItem(IItem item) {
        if(inventory.size() >= 16) {
            // Temporary print.
            System.out.println("Inventory full");
        } else {
            inventory.add(item);
        }
    }

    public List<IItem> getInventory() {
        return inventory;
    }

    public void removeItem(IItem item) {
        Iterator<IItem> itr = inventory.iterator();
        while(itr.hasNext()) {
            IItem element = itr.next();
            if(element == item) {
                itr.remove();
            }
        }
    }
}
