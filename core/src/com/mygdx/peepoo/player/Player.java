package com.mygdx.peepoo.player;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.peepoo.Tile;

public class Player {

    Texture currentTexture,rightFacing, leftFacing, frontFacing, backFacing;
    Tile currentTile;
    Face currentFace;

    int x, y;

    public enum Face {
        Right,
        Left,
        Front,
        Back

    }

    public void setFace(Face face){
        if(face.name().equals("Right")){
            currentTexture = rightFacing;
        }if(face.name().equals("Left")){
            currentTexture = leftFacing;
        }if(face.name().equals("Front")){
            currentTexture = frontFacing;
        }if(face.name().equals("Back")){
            currentTexture = backFacing;
        }

        currentFace = face;
    }

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

    public Texture getCurrentTexture() {
        return currentTexture;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public Face getCurrentFace() {
        return currentFace;
    }
}