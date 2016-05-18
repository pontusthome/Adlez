package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InsufficientGoldException;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
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
        IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 50, 0, 100);
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
        IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 100, 0, 100);
        IEnemy enemy = EnemyFactory.createEnemy(Enemy.REGULAR_LEVEL_ONE, 20, 20);
        IAttack meleeAttack = new MeleeAttack(player);
        assertTrue(player.getGold() == 0);
        enemy.onCollide(meleeAttack);
        assertTrue(player.getGold() == enemy.getGold());
    }

    /**
     * Player equipping weapon. Final sword with 100 bonus damage.
     */
    @Test
    public void testEquipWeapon() throws ItemNotFoundException, InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
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
     * Player equipping armor. Final armor with 100 bonus HP.
     */
    @Test
    public void testEquipArmor() throws ItemNotFoundException, InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
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
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        try {
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
    public void unEquipArmor() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        try {
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
    public void lootItem() throws InventoryFullException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
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
    public void removeItem() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
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

    /**
     * Buy item from shop. Creating npc shop with items.
     * Player has 100 gold.
     * Buys Final sword (100 gold) and Wooden sword (50 gold).
     */
    @Test
    public void buyItem() throws InventoryFullException, InsufficientGoldException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 100, 100);
        List<IItem> items = new ArrayList<>(2);

        items.add(CompleteItems.FINAL_SWORD);
        items.add(CompleteItems.FINAL_BODY_ARMOR);
        NPCShop shop = new NPCShop(items);

        try {
            player.lootItem(shop.sellItem(CompleteItems.WOOD_SWORD, player));
            assertTrue(player.getInventory().get(0) == CompleteItems.WOOD_SWORD);

            player.lootItem(shop.sellItem(CompleteItems.FINAL_SWORD, player));
            assertFalse(player.getInventory().get(1) == CompleteItems.FINAL_SWORD);

        } catch (InsufficientGoldException e) {
            e.getMessage();
        } catch (InventoryFullException e) {
            e.getMessage();
        }
    }

    /**
     * Sell an item to the shop and gain gold.
     * Player has 0 gold and will get 500 gold by selling his sword.
     */
    @Test
    public void sellItem() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 10, 0, 100);
        List<IItem> items = new ArrayList<>(2);

        items.add(CompleteItems.FINAL_SWORD);
        items.add(CompleteItems.FINAL_BODY_ARMOR);
        NPCShop shop = new NPCShop(items);
        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            int value = shop.buyItem(player.getItemInInventory(CompleteItems.FINAL_SWORD), player);
            player.setGold(player.getGold() + value);
            // Should be 500 + current gold (0) ==> 500 gold.
            assertTrue(player.getGold() == 500);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }
}
