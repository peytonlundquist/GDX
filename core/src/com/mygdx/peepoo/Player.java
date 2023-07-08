package com.mygdx.peepoo;

import com.badlogic.gdx.graphics.Texture;

public class Player {

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    Tile currentTile;
    int x, y;

    public Texture getCurrentTexture() {
        return currentTexture;
    }

    public void setCurrentTexture(Texture currentTexture) {
        this.currentTexture = currentTexture;
    }

    Texture currentTexture;

    public Texture getRightFacing() {
        return rightFacing;
    }

    public Texture getLeftFacing() {
        return leftFacing;
    }

    public Texture getFrontFacing() {
        return frontFacing;
    }

    public Texture getBackFacing() {
        return backFacing;
    }

    Texture rightFacing, leftFacing, frontFacing, backFacing;

    public Player(Texture rightFacing, Texture leftFacing, Texture frontFacing, Texture backFacing){
        this.rightFacing = rightFacing;
        this.leftFacing = leftFacing;
        this.frontFacing = frontFacing;
        this.backFacing = backFacing;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}