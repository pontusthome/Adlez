package com.mygdx.game.model;

import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.characters.Player;
import com.mygdx.game.model.characters.actions.IAttack;
import com.mygdx.game.model.characters.actions.MeleeAttack;
import com.mygdx.game.model.core.Direction;

import com.mygdx.game.model.obstacles.IObstacle;
import com.mygdx.game.model.obstacles.Obstacle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by martinso on 22/05/16.
 */

public class ObstacleTest {

    /**
     * Testing destroying obstacle.
     * Player with 5 attack damage attacking obstacle with 10 health points.
     */
    @Test
    public void testDestroyObstacle() {
        IPlayer player = new Player(Direction.NORTH, 0, 17, 17, 0, 0, 100, 5, 0, 100);
        IObstacle obstacle = new Obstacle(100, 100, 32, 32, 10);
        IAttack meleeAttack = new MeleeAttack(player);
        obstacle.onCollide(meleeAttack);
        assertTrue(obstacle.getHealth() == 5);
        obstacle.onCollide(meleeAttack);
        assertTrue(obstacle.getHealth() == 0);
        assertTrue(obstacle.isDestroyed());
    }

}
