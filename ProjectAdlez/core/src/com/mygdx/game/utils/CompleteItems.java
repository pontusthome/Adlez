package com.mygdx.game.utils;

import com.mygdx.game.model.IItem;
import com.mygdx.game.model.Weapon;

/**
 * Created by martinso on 01/05/16.
 */
public final class CompleteItems {

    /**
     * WEAPONS
     */

    /**
     * Wooden sword:
     * 20 bonus damage
     * 50 gold
     */
    static Weapon woodenSword = new Weapon(ItemSignatures.WOODEN_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 20, 50);
    public static final IItem WOODEN_SWORD = woodenSword;

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
     * Wooden body armor:
     * 20 bonus health
     * 50 gold
     */
    static Weapon woodenBody = new Weapon(ItemSignatures.WOODEN_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 20, 50);
    public static final IItem WOODEN_BODY_ARMOR = woodenBody;

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
    public static final IItem FINAL_BODY_ARMOR = woodenBody;

}
