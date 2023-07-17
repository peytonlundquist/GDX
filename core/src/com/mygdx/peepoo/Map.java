package com.mygdx.peepoo;

import com.mygdx.peepoo.npc.Npc;

import java.util.ArrayList;

public class Map {
    Tile[][] tiles;
    int width;
    int height;

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }

    ArrayList<Npc> npcs;

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
        npcs = new ArrayList<>();
    }

}
