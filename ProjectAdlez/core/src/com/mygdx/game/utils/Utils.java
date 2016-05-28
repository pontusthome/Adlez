package com.mygdx.game.utils;

/**
 * Created by Pontus on 2016-04-21.
 */
public final class Utils {

    public static boolean inRange(float x1, float x2, float y1, float y2, int range) {
        return (Math.sqrt(Math.pow(Math.abs(x1-x2),2) + Math.pow(Math.abs(y1-y2),2)) < range);
    }

    public static void addJsonAttribute(StringBuilder JsonObject, String name, Object value) {
        JsonObject.append(name + ":" + value + ",");
    }
}
