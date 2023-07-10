package com.mygdx.peepoo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.peepoo.items.Item;

import java.util.Iterator;

public abstract class Tile {

    protected Animation<TextureRegion> animation;

    protected Sprite sprite;
    protected Boolean walkable;
    protected Boolean interactable;

    public Item interact(){
        return null;
    }

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
