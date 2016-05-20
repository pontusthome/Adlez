package com.mygdx.game.model.characters;

import com.mygdx.game.model.characters.items.IItem;
import com.mygdx.game.model.exceptions.InsufficientGoldException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * Created by martinso on 10/05/16.
 */
public interface INPCShop {

    public IItem sellItem(IItem item, IPlayer player) throws InsufficientGoldException;

    public int buyItem(IItem item, IPlayer player) throws ItemNotFoundException;

    public List<IItem> getItems();

}
