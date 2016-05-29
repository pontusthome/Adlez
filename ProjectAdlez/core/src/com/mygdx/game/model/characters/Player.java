package com.mygdx.game.model.characters;


import com.mygdx.game.model.actions.IAttack;
import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.model.items.Armor;
import com.mygdx.game.model.items.IItem;
import com.mygdx.game.model.items.Weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Michel on 2016-04-19.
 */
public class Player extends Character implements IPlayer {

    private IItem swordEquipped;
    private IItem armorEquipped;
    private boolean isWepSlotEmpty = true;
    private boolean isArmorSlotEmpty = true;
    private List<IItem> inventory;
    private static final int INVENTORY_MAX_SIZE = 16;
    private boolean isInventoryChanged;

    // This constructor should be used.
    public Player() {
        resetPlayer();
    }

    public void resetPlayer() {
        setName("Player");
        setDirection(Direction.NORTH);
        setSpeed(2f);
        setWidth(17);
        setHeight(17);
        setPosX(0);
        setPosY(0);
        setMaxHealth(100);
        setHealth(100);
        setMaxMana(100);
        setMana(100);
        setGold(0);
        setAttackDamage(20);
        setLevel(1);

        inventory = new ArrayList<>(INVENTORY_MAX_SIZE);
        try {
            unEquipArmor(getArmorEquipped());
            unEquipWeapon(getSwordEquipped());
        } catch (InventoryFullException e) {
            e.printStackTrace();
        }
        setIsInventoryChanged();
    }
    
    public void equipItem(IItem item) throws ItemNotFoundException {
        if (item instanceof Weapon) {
            if (isWepSlotEmpty) {
                isWepSlotEmpty = false;
                swordEquipped = item;
                setAttackDamage(getAttackDamage() + item.getStats());
                removeItem(item);
                setIsInventoryChanged();
            }
        } else if (item instanceof Armor) {
            if (isArmorSlotEmpty) {
                isArmorSlotEmpty = false;
                armorEquipped = item;
                setMaxHealth(getMaxHealth() + item.getStats());
                removeItem(item);
            }
        }
    }

    public void unEquipWeapon(IItem item) throws InventoryFullException {
        if (!isWepSlotEmpty) {
            isWepSlotEmpty = true;
            swordEquipped = null;
            setAttackDamage(getAttackDamage() - item.getStats());
            lootItem(item);
            setIsInventoryChanged();
        }
    }

    public void unEquipArmor(IItem item) throws InventoryFullException {
        if (!isArmorSlotEmpty) {
            isArmorSlotEmpty = true;
            armorEquipped = null;
            setMaxHealth(getMaxHealth() - item.getStats());
            lootItem(item);
            setIsInventoryChanged();
        }
    }

    public void lootItem(IItem item) throws InventoryFullException {
        if (inventory.size() >= INVENTORY_MAX_SIZE) {
            throw new InventoryFullException("Inventory Full");
        } else {
            inventory.add(item);
            setIsInventoryChanged();
        }
    }

    public List<IItem> getInventory() {
        return inventory;
    }

    public void removeItem(IItem item) throws ItemNotFoundException {
        inventory.remove(getItemInInventory(item));
        setIsInventoryChanged();
    }

    public IItem getItemInInventory(IItem item) throws ItemNotFoundException {
        Iterator<IItem> itr = inventory.iterator();
        while (itr.hasNext()) {
            IItem element = itr.next();
            if (element == item) {
                return element;
            }
        }
        throw new ItemNotFoundException("No such item in your inventory");
    }

    public IItem getSwordEquipped() {
        return swordEquipped;
    }

    public IItem getArmorEquipped() {
        return armorEquipped;
    }

    @Override
    public void onCollide(Collidable other) {
        super.onCollide(other);
        if (other instanceof IAttack && !((IAttack) other).byPlayer()) {
            IAttack attack = (IAttack) other;
            setHealth(getHealth() - attack.getDamage());
        }
    }

    private void setIsInventoryChanged(){
        isInventoryChanged = true;
    }
    private void resetIsInventoryChanged(){
        isInventoryChanged = false;
    }
    public boolean getIsInventoryChanged(){
        return isInventoryChanged;
    }

    @Override
    public void update(float deltaT){
        super.update(deltaT);
        resetIsInventoryChanged();
    }
}
