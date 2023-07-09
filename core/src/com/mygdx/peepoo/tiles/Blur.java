package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Blur extends Tile {
    public Blur(TextureAtlas textureAtlas){
        super();
        walkable = false;
        sprite = textureAtlas.createSprite("blur");
    }

    @Override
    public String toString() {
        return "blur";
    }
}
