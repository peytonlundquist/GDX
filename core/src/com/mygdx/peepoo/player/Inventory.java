package com.mygdx.peepoo.player;

import com.mygdx.peepoo.items.Weapon;
import com.mygdx.peepoo.items.Potion;

import java.util.ArrayList;

public class Inventory {

    public Inventory(){
        weapons = new ArrayList<>();
        potions = new ArrayList<>();
    }
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    ArrayList<Weapon> weapons;
    ArrayList<Potion> potions;
}
