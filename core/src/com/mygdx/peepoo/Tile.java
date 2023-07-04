package com.mygdx.peepoo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Iterator;

public abstract class Tile {

    protected Animation<TextureRegion> animation;

    protected Sprite sprite;
    protected Boolean walkable;

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Boolean getWalkable() {
        return walkable;
    }

    public abstract String toString();
}
