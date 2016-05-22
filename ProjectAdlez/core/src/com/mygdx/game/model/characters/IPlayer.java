package com.mygdx.game.model.characters;

import com.mygdx.game.model.characters.actions.IAttack;
import com.mygdx.game.model.characters.items.IItem;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * @author Pontus
 */
public interface IPlayer extends ICharacter {

    void equipItem(IItem item) throws ItemNotFoundException;
    void unEquipWeapon(IItem item) throws InventoryFullException;
    void unEquipArmor(IItem item) throws InventoryFullException;
    void lootItem(IItem item) throws InventoryFullException;
    List<IItem> getInventory();
    void removeItem(IItem item) throws ItemNotFoundException;
    IItem getSwordEquipped();
    IItem getArmorEquipped();
}
