package com.mygdx.game.model;

import com.mygdx.game.model.characters.FriendlyNPC;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.NPCShop;
import com.mygdx.game.model.characters.Player;
import com.mygdx.game.model.characters.items.CompleteItems;
import com.mygdx.game.model.characters.items.IItem;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.exceptions.InsufficientGoldException;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by martinso on 22/05/16.
 */
public class NPCShopTest {

    /**
     * Buy item from shop. Creating npc shop with items.
     * Player has 100 gold.
     * Buys Wood sword (50 gold)
     */
    @Test
    public void testBuyItem() throws InventoryFullException, InsufficientGoldException {
        Player player = new Player();
        player.resetPlayer();
        player.setGold(100);
        IFriendlyNPC npc = new FriendlyNPC(Direction.NORTH, 0, 17, 17, 20, 20, 100, 0, 0, 0);
        List<IItem> items = new ArrayList<>(2);

        items.add(CompleteItems.FINAL_SWORD);
        items.add(CompleteItems.FINAL_BODY_ARMOR);
        NPCShop shop = new NPCShop(items);
        npc.createShop(shop);

        try {
            player.lootItem(npc.getShop().sellItem(CompleteItems.WOOD_SWORD, player));
            assertTrue(player.getInventory().get(0) == CompleteItems.WOOD_SWORD);
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
    public void testSellItem() throws InventoryFullException, ItemNotFoundException {
        Player player = new Player();
        player.resetPlayer();
        player.setGold(0);
        IFriendlyNPC npc = new FriendlyNPC(Direction.NORTH, 0, 17, 17, 20, 20, 100, 0, 0, 0);
        List<IItem> items = new ArrayList<>(2);

        items.add(CompleteItems.FINAL_SWORD);
        items.add(CompleteItems.FINAL_BODY_ARMOR);
        NPCShop shop = new NPCShop(items);
        npc.createShop(shop);

        try {
            player.lootItem(CompleteItems.FINAL_SWORD);
            int value = npc.getShop().buyItem(player.getItemInInventory(CompleteItems.FINAL_SWORD), player);
            player.setGold(player.getGold() + value);
            // Should be 500 + current gold (0) ==> 500 gold.
            assertTrue(player.getGold() == 500);
        } catch (InventoryFullException e) {
            e.getMessage();
        } catch (ItemNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Get all items from the shop.
     */
    @Test
    public void testGetItems() {
        IFriendlyNPC npc = new FriendlyNPC(Direction.NORTH, 0, 17, 17, 20, 20, 100, 0, 0, 0);
        List<IItem> items = new ArrayList<>(3);
        items.add(CompleteItems.FINAL_SWORD);
        items.add(CompleteItems.FINAL_BODY_ARMOR);
        items.add(CompleteItems.WOOD_SWORD);
        NPCShop shop = new NPCShop(items);
        npc.createShop(shop);
        assertTrue(npc.getShop().getItems().size() == 3);
    }
}
