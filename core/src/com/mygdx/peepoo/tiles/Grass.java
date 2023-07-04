package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Grass extends Tile {
    public Grass(TextureAtlas textureAtlas){
        walkable = true;
        sprite = textureAtlas.createSprite("grass");
        //decor = new com.mygdx.peepoo.tiles.decors.Grass();
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
