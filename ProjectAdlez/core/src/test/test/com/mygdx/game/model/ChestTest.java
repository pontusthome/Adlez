package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InventoryFullException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by martinso on 18/05/16.
 */

public class ChestTest {

    /**
     * Testing the chest.
     * Adding, removing and looting.
     */
    @Test
    public void testChest() throws InventoryFullException {
        IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 50, 0, 100);
        IChest chest = new Chest(100, 100, 10, 10, 2, 10);

        try {
            assertTrue(chest.isEmpty());
            chest.addItem(CompleteItems.FINAL_SWORD);
            assertFalse(chest.isEmpty());
            assertFalse(chest.isFull());
            chest.addItem(CompleteItems.FINAL_BODY_ARMOR);
            assertTrue(chest.isFull());

            chest.removeItem(CompleteItems.FINAL_SWORD);
            assertFalse(chest.isFull());

            chest.addItem(CompleteItems.FINAL_SWORD);
            assertTrue(chest.isFull());

            IInteraction interactionChest = new Interaction(player);
            chest.onCollide(interactionChest);
            // Looted all items from the chest (2), so players inventory size = 2.
            assertTrue(player.getInventory().size() == 2);

        } catch (InventoryFullException e) {
            e.getMessage();
        }

    }

}
