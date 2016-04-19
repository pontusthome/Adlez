package com.mygdx.game.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.view.CharacterView;
import org.junit.After;
import org.junit.Before;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * @author Pontus
 */
public class PlayerControllerTest {

    Vector2 position;
    CharacterView characterView;
    PlayerController playerController;
    Robot robot = new Robot();

    public PlayerControllerTest() throws AWTException {
    }

    @Before
    public void setUp() throws Exception {
        Vector2 position = new Vector2(0, 0);
        characterView = new CharacterView(position);
        playerController = new PlayerController(characterView);

    }

    @After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void moveForward() throws Exception {
        playerController.move(0, 2f);
        Vector2 newPosition = characterView.getCharacterPosition();
        System.out.println(newPosition.toString());
        assertTrue(new Vector2(0, 2).equals(newPosition));
    }

}