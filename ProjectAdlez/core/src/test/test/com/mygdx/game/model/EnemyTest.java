package com.mygdx.game.model;

import com.mygdx.game.model.characters.Enemy;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.characters.Player;
import com.mygdx.game.model.core.Direction;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by martinso on 25/05/16.
 */
public class EnemyTest {

    private float deltaT = 0.02f;

    /**
     * Basic method to set players position.
     */
    public void setPlayerPos(IPlayer player, float x, float y) {
        player.resetPlayer();
        player.setPosX(x);
        player.setPosY(y);
    }

    public void resetEnemyPos(IEnemy enemy) {
        enemy.setPosX(0);
        enemy.setPosY(0);
    }

    /**
     * Testing the different types of enemies.
     */
    @Test
    public void testEnemyType() {
        IPlayer player = new Player();
        player.resetPlayer();
        player.setAttackDamage(10);
        IEnemy enemy1 = new Enemy(player, Direction.NORTH, 2, 17, 17, 0, 0, 20, 300, 0, 0, Enemy.REGULAR_LEVEL_ONE);
        IEnemy enemy2 = new Enemy(player, Direction.NORTH, 2, 17, 17, 0, 0, 20, 300, 0, 0, Enemy.REGULAR_LEVEL_TWO);
        IEnemy enemy3 = new Enemy(player, Direction.NORTH, 2, 17, 17, 0, 0, 20, 300, 0, 0, Enemy.DARK_ONE_LEVEL_ONE);
        IEnemy enemy4 = new Enemy(player, Direction.NORTH, 2, 17, 17, 0, 0, 20, 300, 0, 0, Enemy.DOG_LEVEL_ONE);

        assertTrue(enemy1.getType() == Enemy.REGULAR_LEVEL_ONE);
        assertTrue(enemy2.getType() == Enemy.REGULAR_LEVEL_TWO);
        assertTrue(enemy3.getType() == Enemy.DARK_ONE_LEVEL_ONE);
        assertTrue(enemy4.getType() == Enemy.DOG_LEVEL_ONE);
    }

    /**
     * Test if enemy follows the player.
     * Enemy start on 0, 0
     */
    @Test
    public void testEnemyFollow() {
        IPlayer player = new Player();
        IEnemy enemy = new Enemy(player, Direction.NORTH, 2, 17, 17, 0, 0, 20, 300, 0, 0, Enemy.REGULAR_LEVEL_ONE);

        // Player is North of the enemy.
        setPlayerPos(player, 0, 30);
        enemy.followPlayer();
        enemy.move(deltaT);
        assertTrue(enemy.getPosY() > 0);

        // Player is West of the enemy.
        resetEnemyPos(enemy);
        setPlayerPos(player, -30, 0);
        enemy.followPlayer();
        enemy.move(deltaT);
        assertTrue(enemy.getPosX() < 0);

        // Player is East of the enemy.
        setPlayerPos(player, 30, 0);
        resetEnemyPos(enemy);
        enemy.followPlayer();
        enemy.move(deltaT);
        assertTrue(enemy.getPosX() > 0);

        // Player is South of the enemy.
        setPlayerPos(player, 0, -30);
        resetEnemyPos(enemy);
        enemy.followPlayer();
        enemy.move(deltaT);
        assertTrue(enemy.getPosY() < 0);
    }

}
