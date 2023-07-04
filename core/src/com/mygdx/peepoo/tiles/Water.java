package com.mygdx.peepoo.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.peepoo.Tile;

import java.util.LinkedList;

public class Water extends Tile {


    public Water(TextureAtlas textureAtlas){
        int FRAME_COLS = 11;
        int FRAME_ROWS = 1;

        walkable = false;
        sprite = textureAtlas.createSprite("water");
        animation = new Animation<TextureRegion>(1f, new TextureRegion[FRAME_COLS * FRAME_ROWS]);
        Texture walkSheet = new Texture(Gdx.files.internal("water.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        animation = new Animation<TextureRegion>(1f, walkFrames);
    }

    @Override
    public String toString() {
        return "Water";
    }
}
