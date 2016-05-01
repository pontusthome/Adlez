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
     * Wooden sword: 10 bonus damage
     */
    static Weapon woodenSword = new Weapon(ItemSignatures.WOODEN_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 10);
    public static final IItem WOODEN_SWORD = woodenSword;

    /**
     * Iron sword: 20 bonus damage
     */
    static Weapon ironSword = new Weapon(ItemSignatures.IRON_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 20);
    public static final IItem IRON_SWORD = ironSword;

    /**
     * Final sword: 100 bonus damage
     */
    static Weapon finalSword = new Weapon(ItemSignatures.FINAL_SWORD, ItemSignatures.TYPE_WEAPON_SWORD, 20);
    public static final IItem FINAL_SWORD = finalSword;

    /**
     * ARMOR
     */

    /**
     * Wooden body armor: 10 bonus health
     */
    static Weapon woodenBody = new Weapon(ItemSignatures.WOODEN_BODY_ARMOR, ItemSignatures.TYPE_ARMOR_BODY, 10);
    public static final IItem WOODEN_BODY_ARMOR = woodenBody;

    // add more...

}
