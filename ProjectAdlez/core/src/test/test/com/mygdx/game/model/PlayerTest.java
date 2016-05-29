package com.mygdx.game.model;

import com.mygdx.game.model.characters.*;
import com.mygdx.game.model.actions.IAttack;
import com.mygdx.game.model.actions.MeleeAttack;
import com.mygdx.game.model.items.CompleteItems;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.model.factories.EnemyFactory;
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
    public void testTakeDamage() {
        Player player = new Player();
        player.resetPlayer();
        player.setMaxHealth(100);
        IEnemy enemy = EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 20, 20);
        IAttack meleeAttack = new MeleeAttack(enemy);
        player.onCollide(meleeAttack);
        assertTrue(player.getHealth() == 95);
    }

    /**
     * Player killing an enemy.
     * Player has 50 attack damage and enemy has 100 HP.
     */
    @Test
    public void testKillEnemy() {
        Player player = new Player();
        player.resetPlayer();
        player.setAttackDamage(50);
        IEnemy enemy = EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 20, 20);
        IAttack meleeAttack = new MeleeAttack(player);
        enemy.onCollide(meleeAttack);
        assertTrue(enemy.getHealth() == 50);
        enemy.onCollide(meleeAttack);
        assertTrue(enemy.getHealth() == 0);
        assertFalse(enemy.isAlive());
    }

    /**
     * Player killing an enemy and looting the gold.
     * Player starts with 0 gold.
     */
    @Test
    public void testLootGoldFromEnemy() {
        Player player = new Player();
        player.resetPlayer();
        player.setGold(0);
        player.setAttackDamage(1337);
        IEnemy enemy = EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 20, 20);
        IAttack meleeAttack = new MeleeAttack(player);
        assertTrue(player.getGold() == 0);
        enemy.onCollide(meleeAttack);
        assertTrue(player.getGold() == enemy.getGold());
    }

    /**
     * Player has base attack damage 10.
     * Equipping weapon.
     * Final sword with 100 bonus damage.
     */
    @Test
    public void testEquipWeapon() throws ItemNotFoundException, InventoryFullException {
        Player player = new Player();
        player.resetPlayer();
        player.setAttackDamage(10);
        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            player.equipItem(CompleteItems.FINAL_SWORD);
            assertTrue(player.getAttackDamage() == 110);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Player has 100 base health points.
     * Equipping armor.
     * Final armor with 100 bonus HP.
     */
    @Test
    public void testEquipArmor() throws ItemNotFoundException, InventoryFullException {
        Player player = new Player();
        player.resetPlayer();
        player.setMaxHealth(100);
        try {
            player.lootItem(CompleteItems.FINAL_BODY_ARMOR);
            player.equipItem(CompleteItems.FINAL_BODY_ARMOR);
            assertTrue(player.getMaxHealth() == 200);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Player unequipping weapon. Final weapon with 100 bonus damage.
     */
    @Test
    public void testUnEquipWeapon() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player();
        player.resetPlayer();
        player.setAttackDamage(10);
        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            player.equipItem(CompleteItems.FINAL_SWORD);
            player.unEquipWeapon(player.getSwordEquipped());
            assertTrue(player.getAttackDamage() == 10);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Player unequipping armor. Final armor with 100 bonus HP.
     */
    @Test
    public void testUnEquipArmor() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player();
        player.resetPlayer();
        player.setMaxHealth(100);
        try {
            player.lootItem(CompleteItems.FINAL_BODY_ARMOR);
            player.equipItem(CompleteItems.FINAL_BODY_ARMOR);
            player.unEquipArmor(player.getArmorEquipped());
            assertTrue(player.getMaxHealth() == 100);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Player looting item.
     */
    @Test
    public void testLootItem() throws InventoryFullException {
        Player player = new Player();
        player.resetPlayer();
        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            assertTrue(player.getInventory().size() == 1);
        } catch (InventoryFullException e) {
            e.getMessage();
        }
    }

    /**
     * Remove item from inventory.
     */
    @Test
    public void testRemoveItem() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player();
        player.resetPlayer();
        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            assertTrue(player.getInventory().size() == 1);
            player.removeItem(CompleteItems.FINAL_SWORD);
            assertTrue(player.getInventory().size() == 0);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }
}
