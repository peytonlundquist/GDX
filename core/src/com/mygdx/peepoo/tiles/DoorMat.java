package com.mygdx.peepoo.tiles;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Map;
import com.mygdx.peepoo.Tile;

public class DoorMat extends Tile {

    public Map getOutside() {
        return outside;
    }

    private Map outside;

    public DoorMat(Map outside, TextureAtlas textureAtlas){
        this.outside = outside;
        walkable = true;
        sprite = textureAtlas.createSprite("doorMat");
    }

    @Override
    public String toString() {
        return "doorMat";
    }
}
