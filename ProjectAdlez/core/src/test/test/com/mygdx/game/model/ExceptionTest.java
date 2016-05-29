package com.mygdx.game.model;

import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.characters.NPCShop;
import com.mygdx.game.model.characters.Player;
import com.mygdx.game.model.items.CompleteItems;
import com.mygdx.game.model.items.IItem;
import com.mygdx.game.model.exceptions.InsufficientGoldException;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by martinso on 25/05/16.
 */
public class ExceptionTest {

    /**
     * Player has 0 gold and tries to buy an item.
     */
    @Test
    public void testInsufficientGoldException() {
        Throwable e = null;

        IPlayer player = new Player();
        player.resetPlayer();
        player.setGold(0);

        List<IItem> items = new ArrayList<IItem>();
        items.add(CompleteItems.WOOD_SWORD);
        NPCShop npcShop = new NPCShop(items);

        try {
            npcShop.sellItem(CompleteItems.WOOD_SWORD, player);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof InsufficientGoldException);
    }

    /**
     * Player's inventory capacity is 16 slots.
     * Testing the exception by adding 17 items.
     */
    @Test
    public void testInventoryFullException() {
        Throwable e = null;

        IPlayer player = new Player();
        player.resetPlayer();

        try {
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);
            player.lootItem(CompleteItems.WOOD_SWORD);

        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof InventoryFullException);
    }

    /**
     * Player tries to remove an item that is not in the inventory.
     */
    @Test
    public void testItemNotFoundException() {
        Throwable e = null;

        IPlayer player = new Player();
        player.resetPlayer();

        try {
            player.removeItem(CompleteItems.WOOD_SWORD);
        } catch (Throwable ex) {
            e = ex;
        }
        assertTrue(e instanceof ItemNotFoundException);
    }
}
