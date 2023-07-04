package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

import java.util.LinkedList;

public class Water extends Tile {

    public Water(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("water");
    }

    @Override
    public String toString() {
        return "Water";
    }
}
