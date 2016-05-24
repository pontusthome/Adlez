package com.mygdx.game.model;

import com.mygdx.game.model.characters.Player;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.characters.actions.Interaction;
import com.mygdx.game.model.characters.items.CompleteItems;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.obstacles.Chest;
import com.mygdx.game.model.obstacles.IChest;
import org.junit.Test;

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
        Player player = new Player();
        player.resetPlayer();
        IChest chest = new Chest(100, 100, 10, 10, 2);

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
