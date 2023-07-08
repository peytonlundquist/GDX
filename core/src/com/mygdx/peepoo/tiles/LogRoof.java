package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class LogRoof extends Tile {
    public LogRoof(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("logRoof");    }

    @Override
    public String toString() {
        return "LogRoof";
    }
}
