package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.items.Item;
import com.mygdx.peepoo.player.Inventory;
import com.mygdx.peepoo.Tile;
import com.mygdx.peepoo.items.weapons.GreatSword;

public class Chest extends Tile {

    public Chest(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("chest");
        interactable = true;
    }

    @Override
    public Item interact() {
        return new GreatSword();
    }

    @Override
    public String toString() {
        return "chest";
    }
}
