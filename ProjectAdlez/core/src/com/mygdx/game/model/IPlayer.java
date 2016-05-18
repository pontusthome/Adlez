package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * @author Pontus
 */
public interface IPlayer extends ICharacter {

    public void equipItem(IItem item) throws ItemNotFoundException;
    public void unEquipWeapon(IItem item) throws InventoryFullException;
    public void unEquipArmor(IItem item) throws InventoryFullException;
    public void lootItem(IItem item) throws InventoryFullException;
    public List<IItem> getInventory();
    public void removeItem(IItem item) throws ItemNotFoundException;
    public IItem getSwordEquipped();
    public IItem getArmorEquipped();
}
