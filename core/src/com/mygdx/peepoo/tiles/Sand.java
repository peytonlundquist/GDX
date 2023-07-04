package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Sand extends Tile {

    public Sand(TextureAtlas textureAtlas){
        walkable = true;
        sprite = textureAtlas.createSprite("sand");
    }

    @Override
    public String toString() {
        return "Sand";
    }
}
