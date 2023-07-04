package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Empty extends Tile {
    public Empty(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("empty");
    }

    @Override
    public String toString() {
        return "Empty";
    }
}
