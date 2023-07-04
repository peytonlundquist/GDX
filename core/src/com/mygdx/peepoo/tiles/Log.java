package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Log extends Tile {
    public Log(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("log");    }

    @Override
    public String toString() {
        return "Log";
    }
}
