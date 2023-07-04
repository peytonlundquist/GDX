package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Dirt extends Tile {
    public Dirt(TextureAtlas textureAtlas){
        super();
        walkable = true;
        sprite = textureAtlas.createSprite("dirt");
    }

    @Override
    public String toString() {
        return "dirt";
    }
}
