package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.WorldObject;
import com.mygdx.game.model.characters.IPlayer;

import java.io.Serializable;

/**
 * Created by martinso on 15/05/16.
 */
public class ManaFountain extends WorldObject implements IManaFountain, Serializable {

    public ManaFountain(float posX, float posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof IInteraction) {
            IInteraction interaction = (IInteraction) other;
            if (interaction.getCharacter() instanceof IPlayer) {
                IPlayer player = (IPlayer) interaction.getCharacter();
                // Sets players current mana to max.
                player.setMana(100);
            }
        }
    }
}
