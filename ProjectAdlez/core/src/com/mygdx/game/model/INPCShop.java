package com.mygdx.game.model;

import com.mygdx.game.model.exceptions.InsufficientGoldException;

import java.util.List;

/**
 * Created by martinso on 10/05/16.
 */
public interface INPCShop {

    public IItem sellItem(IItem item, IPlayer player) throws InsufficientGoldException;

    public int buyItem(IItem item, IPlayer player);

    public List<IItem> getItems();

}
