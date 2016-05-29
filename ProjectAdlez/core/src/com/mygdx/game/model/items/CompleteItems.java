package com.mygdx.game.model.items;

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
    public static final Weapon WOOD_SWORD = woodSword;

    /**
     * Iron sword:
     * 60 bonus damage
     * 200 gold
     */
    static Weapon ironSword = new Weapon(ItemSignatures.IRON_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 60, 200);
    public static final Weapon IRON_SWORD = ironSword;

    /**
     * Final sword:
     * 100 bonus damage
     * 500 gold
     */
    static Weapon finalSword = new Weapon(ItemSignatures.FINAL_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 100, 500);
    public static final Weapon FINAL_SWORD = finalSword;

    /**
     * ARMOR
     */

    /**
     * Cloth body armor:
     * 20 bonus health
     * 50 gold
     */
    static Armor clothBody = new Armor(ItemSignatures.CLOTH_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 20, 50);
    public static final Armor CLOTH_BODY_ARMOR = clothBody;

    /**
     * Iron body armor:
     * 50 bonus health
     * 200 gold
     */
    static Armor ironBody = new Armor(ItemSignatures.IRON_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 50, 200);
    public static final Armor IRON_BODY_ARMOR = ironBody;

    /**
     * Final body armor:
     * 100 bonus health
     * 500 gold
     */
    static Armor finalBody = new Armor(ItemSignatures.FINAL_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 100, 500);
    public static final Armor FINAL_BODY_ARMOR = finalBody;

}
