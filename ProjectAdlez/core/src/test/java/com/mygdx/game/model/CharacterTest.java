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
        player.move(2, 3);
        assertTrue(player.getPosX() == 2);
        assertTrue(player.getPosY() == 3);
        player.move(3, 2);
        assertTrue(player.getPosX() == 5);
        assertTrue(player.getPosY() == 5);
    }

}