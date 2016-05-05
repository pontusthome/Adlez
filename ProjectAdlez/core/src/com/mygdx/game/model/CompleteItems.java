package com.mygdx.game.model;

import com.mygdx.game.model.IItem;
import com.mygdx.game.model.Weapon;
import com.mygdx.game.utils.ItemSignatures;

/**
 * Created by martinso on 01/05/16.
 */
public final class CompleteItems {

    /**
     * final class holding all items and their stats.
     */

    /**
     * WEAPONS
     */

    /**
     * Wooden sword:
     * 20 bonus damage
     * 50 gold
     */
    static Weapon woodSword = new Weapon(ItemSignatures.WOOD_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 20, 50);
    public static final IItem WOOD_SWORD = woodSword;

    /**
     * Iron sword:
     * 60 bonus damage
     * 200 gold
     */
    static Weapon ironSword = new Weapon(ItemSignatures.IRON_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 60, 200);
    public static final IItem IRON_SWORD = ironSword;

    /**
     * Final sword:
     * 100 bonus damage
     * 500 gold
     */
    static Weapon finalSword = new Weapon(ItemSignatures.FINAL_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 100, 500);
    public static final IItem FINAL_SWORD = finalSword;

    /**
     * ARMOR
     */

    /**
     * Cloth body armor:
     * 20 bonus health
     * 50 gold
     */
    static Weapon clothBody = new Weapon(ItemSignatures.CLOTH_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 20, 50);
    public static final IItem CLOTH_BODY_ARMOR = clothBody;

    /**
     * Iron body armor:
     * 50 bonus health
     * 200 gold
     */
    static Weapon ironBody = new Weapon(ItemSignatures.IRON_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 50, 200);
    public static final IItem IRON_BODY_ARMOR = ironBody;

    /**
     * Final body armor:
     * 100 bonus health
     * 500 gold
     */
    static Weapon finalBody = new Weapon(ItemSignatures.FINAL_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 100, 500);
    public static final IItem FINAL_BODY_ARMOR = finalBody;

}
