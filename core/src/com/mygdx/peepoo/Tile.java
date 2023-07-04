package com.mygdx.peepoo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Iterator;

public abstract class Tile {
    public Sprite getSprite() {
        return sprite;
    }

    protected Sprite sprite;
    protected Boolean walkable;
    protected Iterator<Texture> iterator;

    public Boolean getWalkable() {
        return walkable;
    }

    public abstract String toString();
}
