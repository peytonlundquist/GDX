package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.Map;
import com.mygdx.peepoo.Tile;

public class Door extends Tile {

    public boolean isOpen() {
        return open;
    }

    boolean open;
    Map outside;
    Map inside;

    public Map getInside() {
        return inside;
    }

    public void setInside(Map inside) {
        this.inside = inside;
    }

    public Door(Map inside, TextureAtlas textureAtlas){
        this.inside = inside;
        walkable = true;
        sprite = textureAtlas.createSprite("door");
        open = false;
    }

    public Door(TextureAtlas textureAtlas){
        walkable = true;
        sprite = textureAtlas.createSprite("door");
        open = true;
    }

    public void openDoor(){
        //texture = new Texture("tiles/doorOpen.png");
        open = true;
    }

    public void closeDoor(){
        //texture = new Texture("tiles/door.png");
        open = false;
    }

    @Override
    public String toString() {
        return "Door";
    }
}
