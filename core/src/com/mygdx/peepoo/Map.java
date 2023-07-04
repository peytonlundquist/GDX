package com.mygdx.peepoo;

public class Map {
    Tile[][] tiles;
    int width;
    int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    Map(int x, int y){
        tiles = new Tile[x][y];
        width = x;
        height = y;
    }

}
