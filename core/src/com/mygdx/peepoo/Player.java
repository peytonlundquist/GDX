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

    public void setRightFacing(Texture rightFacing) {
        this.rightFacing = rightFacing;
    }

    public Texture getLeftFacing() {
        return leftFacing;
    }

    public void setLeftFacing(Texture leftFacing) {
        this.leftFacing = leftFacing;
    }

    public Texture getFrontFacing() {
        return frontFacing;
    }

    public void setFrontFacing(Texture frontFacing) {
        this.frontFacing = frontFacing;
    }

    public Texture getBackFacing() {
        return backFacing;
    }

    public void setBackFacing(Texture backFacing) {
        this.backFacing = backFacing;
    }

    Texture rightFacing, leftFacing, frontFacing, backFacing;

    public Player(Texture rightFacing, Texture leftFacing, Texture frontFacing, Texture backFacing){
        this.rightFacing = rightFacing;
        this.leftFacing = leftFacing;
        this.frontFacing = frontFacing;
        this.backFacing = backFacing;
//        x = 270;
//        y = 357;
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
