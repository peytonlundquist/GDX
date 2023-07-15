package com.mygdx.peepoo.npc;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Reaper extends Npc {

    public Reaper(TextureAtlas textureAtlas) {
        sprite = textureAtlas.createSprite("reaper");
        dialogueTree = new BinaryTree();
        dialogueTree.add(null, "Hello.", "Hello", "Hi");
        dialogueTree.add("Hello", "What do you want.", "Who are you?", "Idk");
        dialogueTree.add("Hi", "What?", "Hello", "Hi");
        currentNode = dialogueTree.getRoot();
    }
}