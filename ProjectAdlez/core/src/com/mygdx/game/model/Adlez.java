package com.mygdx.game.model;

/**
 * Created by Pontus on 2016-04-19.
 *
 * Singleton of the model Adlez
 */
public class Adlez {
    private static Adlez adlez = new Adlez();

    public static Adlez getInstance() {
        return adlez;
    }

    private Player player;

    private Adlez() {
        player = new Player();
    }

    public Player getPlayer() {
        return player;
    }
}
