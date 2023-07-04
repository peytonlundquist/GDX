package com.mygdx.peepoo.tiles;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.mygdx.peepoo.Tile;

public class SandOceanEdge extends Tile {
    public SandOceanEdge(TextureAtlas textureAtlas){
        walkable = false;
        sprite = textureAtlas.createSprite("sandOceanEdge");
    }

    @Override
    public String toString() {
        return "SandOceanEdge";
    }
}
