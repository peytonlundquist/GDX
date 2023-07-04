package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Tile;

public class Grass extends Tile {
    public Grass(TextureAtlas textureAtlas, int variation){
        walkable = true;

        switch(variation) {
            case (1):
                sprite = textureAtlas.createSprite("grass_rock");
                break;
            case (2):
                sprite = textureAtlas.createSprite("grass_roses");
                break;
            case (3):
                sprite = textureAtlas.createSprite("grass_three_sunflower");
                break;
            case (4):
                sprite = textureAtlas.createSprite("grass_two_sunflower");
                break;
            default:
                sprite = textureAtlas.createSprite("grass");
                break;
        }
        //decor = new com.mygdx.peepoo.tiles.decors.Grass();
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
