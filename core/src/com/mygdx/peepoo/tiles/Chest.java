package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.items.Item;
import com.mygdx.peepoo.player.Inventory;
import com.mygdx.peepoo.Tile;
import com.mygdx.peepoo.items.weapons.GreatSword;

public class Chest extends Tile {

    boolean opened;
    Item item;

    public Chest(TextureAtlas textureAtlas, Item item){
        walkable = false;
        sprite = textureAtlas.createSprite("chest");
        interactable = true;
        opened = false;
        this.item = item;
    }

    @Override
    public Item interact() {
        opened = true;
        return item;
    }

    public boolean isOpened() {
        return opened;
    }

    @Override
    public String toString() {
        return "chest";
    }
}
