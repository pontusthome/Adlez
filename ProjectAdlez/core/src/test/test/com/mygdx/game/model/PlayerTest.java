package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InventoryFullException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by martinso on 16/05/16.
 */

public class PlayerTest {



    /**
     * Player with 100 HP taking 5 damage from Enemy 'Dark enemy level one'.
     */
    @Test
    public void takeDamage() {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        Enemy enemy = EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 20, 20);
        IAttack meleeAttack = new MeleeAttack(enemy);
        player.onCollide(meleeAttack);
        assertTrue(player.getHealth() == 95);
    }

    /**
     * Player equipping weapon. Final sword with 100 bonus damage.
     */
    @Test
    public void equipWeapon() {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        player.equipItem(CompleteItems.FINAL_SWORD);
        assertTrue(player.getAttackDamage() == 110);
    }

    /**
     * Player equipping armor. Final armor with 100 bonus HP.
     */
    @Test
    public void equipArmor() {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        player.equipItem(CompleteItems.FINAL_BODY_ARMOR);
        assertTrue(player.getMaxHealth() == 200);
    }

    /**
     * Player unequipping weapon. Final weapon with 100 bonus damage.
     */
    @Test
    public void unEquipWeapon() throws InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        player.equipItem(CompleteItems.FINAL_SWORD);
        player.unEquipWeapon(player.getSwordEquipped());
        assertTrue(player.getAttackDamage() == 10);
    }

    /**
     * Player unequipping armor. Final armor with 100 bonus HP.
     */
    @Test
    public void unEquipArmor() throws InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        player.equipItem(CompleteItems.FINAL_BODY_ARMOR);
        player.unEquipArmor(player.getArmorEquipped());
        assertTrue(player.getMaxHealth() == 100);
    }

    /**
     * Player looting item.
     */
    @Test
    public void lootItem() throws InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        player.lootItem(CompleteItems.FINAL_SWORD);
        assertTrue(player.getInventory().size() == 1);
    }


}
