package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.IFriendlyNPC;
import com.mygdx.game.view.FriendlyNPCView;

import java.util.List;

/**
 * Created by martinso on 09/05/16.
 */
public class FriendlyNPCController implements IController {

    private FriendlyNPCView friendlyNPCView;
    private List<IFriendlyNPC> npcs;

    public FriendlyNPCController(List<IFriendlyNPC> npcs, String npcImg) {
        this.npcs = npcs;
        friendlyNPCView = new FriendlyNPCView(npcImg);
    }

    @Override
    public void update() {
        // Nothing for now.
    }

    @Override
    public void render(SpriteBatch batch) {
        friendlyNPCView.generateFriendlyNPC(npcs, batch);
    }
}
