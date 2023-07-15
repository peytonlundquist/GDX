package com.mygdx.peepoo;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.peepoo.items.weapons.Dagger;
import com.mygdx.peepoo.items.weapons.GreatSword;
import com.mygdx.peepoo.tiles.*;

import java.util.ArrayList;
import java.util.Random;

public class Maps {
    public static Map getBasicMap(TextureAtlas textureAtlas){

        int oceanBorderSize = 10;
        int beachBorderSize = 7;
        int size = 50;
        Map map = new Map(size, size);
        Random random = new Random();

        Water water = new Water(textureAtlas);
        SandOceanEdge sandOceanEdge = new SandOceanEdge(textureAtlas);
        Sand sand = new Sand(textureAtlas);
        Dirt dirt = new Dirt(textureAtlas);

        ArrayList<Grass> grasses = new ArrayList<Grass>();
        grasses.add(new Grass(textureAtlas, 0));
        grasses.add(new Grass(textureAtlas, 1));
        grasses.add(new Grass(textureAtlas, 2));
        grasses.add(new Grass(textureAtlas, 3));
        grasses.add(new Grass(textureAtlas, 4));

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                    /* Ocean */
                if(i < oceanBorderSize || j < oceanBorderSize || i > size - oceanBorderSize - 2  || j > size - oceanBorderSize - 2) {
                    map.tiles[i][j] = water;


                    /* Beach */
                }else if(i > oceanBorderSize - 1 && i < beachBorderSize + oceanBorderSize ||
                        j > oceanBorderSize - 1 && j < beachBorderSize + oceanBorderSize){

                    if(j == oceanBorderSize){
                        map.tiles[i][j] = sandOceanEdge;
                    }else{
                        map.tiles[i][j] = sand;
                    }

                    /* Lake */
                }else if (i == size/2 && j == size/2) {
                    map.tiles[i - 1][j] = water;
                    map.tiles[i][j - 1] = water;
                    map.tiles[i - 1][j - 1] = water;
                    map.tiles[i][j] = water;

                }else{
                    if(random.nextInt(0, 4) == 1){
                        map.tiles[i][j] = dirt;
                    }else{
                        map.tiles[i][j] = grasses.get(random.nextInt(0, 4));
                    }
                }
            }
        }

        addHouse(textureAtlas, 16, 16, map);

        return map;
    }

    public static void addHouse(TextureAtlas textureAtlas, int x, int y, Map map){
        Empty empty = new Empty(textureAtlas);


        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                map.tiles[x + i][y + j] = new Log(textureAtlas);
            }
        }

        for(int i = 0; i < 9; i++){
            map.tiles[x + i][y + 3] = new LogRoof(textureAtlas);
            map.tiles[x + i][y + 4] = new LogRoof(textureAtlas);
        }
        map.tiles[x + 4][y] = new Door(getHome(textureAtlas, map, x, y), textureAtlas);
        map.tiles[x + 4][y + 1] = empty;
    }

    public static Map getHome(TextureAtlas textureAtlas, Map outside, int x, int y){
        Empty empty = new Empty(textureAtlas);
        Dirt dirt = new Dirt(textureAtlas);
        Log log = new Log(textureAtlas);
        int size = 50;
        Map map = new Map(size, size);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                /* Ocean */
                if ((i < x || j < y) || (i > x + 8 || j > y + 6)) {
                    map.tiles[i][j] = empty;

                } else {
                    if(j > y + 3){
                        map.tiles[i][j] = log;
                    }else{
                        map.tiles[i][j] = dirt;
                    }
                }
            }
        }
        map.tiles[x + 8][y + 3] = new Chest(textureAtlas, new GreatSword());
        map.tiles[x + 8][y + 2] = new Chest(textureAtlas, new Dagger());

        map.tiles[x + 4][y - 1] = new DoorMat(outside, textureAtlas);
        return map;
    }
}
