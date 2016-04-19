package com.mygdx.game.model;

/**
 * Created by Pontus on 2016-04-19.
 *
 * Singleton of the model Adlez
 */
public class Adlez {
    private static final Adlez INSTANCE = new Adlez();

    private Adlez() {}

    public static Adlez getInstance() {
        return INSTANCE;
    }
}
