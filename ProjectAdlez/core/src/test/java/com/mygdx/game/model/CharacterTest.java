package com.mygdx.game.model;

import static org.junit.Assert.*;

/**
 * Created by Pontus on 2016-04-19.
 */
public class CharacterTest {

    Player player;

    @org.junit.Before
    public void setUp() throws Exception {
        player = new Player();
        player.setPosX(0);
        player.setPosY(0);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void move() throws Exception {
        player.moveEast();
        assertTrue(player.getPosX() == player.getSpeed());
        assertTrue(player.getPosY() == 0);
        player.moveWest();
        assertTrue(player.getPosX() == 0);
        assertTrue(player.getPosY() == 0);
        player.moveWest();
        assertTrue(player.getPosX() == -player.getSpeed());
        assertTrue(player.getPosY() == 0);
        player.moveSouth();
        assertTrue(player.getPosX() == -player.getSpeed());
        assertTrue(player.getPosY() == -player.getSpeed());
        player.moveNorth();
        assertTrue(player.getPosX() == -player.getSpeed());
        assertTrue(player.getPosY() == 0);
    }

}