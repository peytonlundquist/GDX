package com.mygdx.peepoo.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Npc {
    protected BinaryTree dialogueTree;
    protected BinaryTree.Node currentNode;
    protected Sprite sprite;

    public Npc(){
//        this.sprite = sprite;
//        this.dialogueTree = dialogueTree;
//        currentNode = dialogueTree.getRoot();
    }

    public BinaryTree.Node interact(String response){
        if(response == currentNode.leftResponse){
            currentNode = currentNode.leftNode;
        }else{
            currentNode = currentNode.rightNode;
        }

        return currentNode;
    }

}
