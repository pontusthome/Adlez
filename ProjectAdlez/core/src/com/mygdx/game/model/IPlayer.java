package com.mygdx.game.model;

import java.util.List;

/**
 * @author Pontus
 */
public interface IPlayer extends ICharacter {

    public void equipItem(IItem item);
    public void unEquipWeapon(IItem item);
    public void unEquipArmor(IItem item);
    public void lootItem(IItem item);
    public List<IItem> getInventory();
    public void removeItem(IItem item);
    public IItem getSwordEquipped();
    public IItem getArmorEquipped();
}
