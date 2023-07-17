package com.mygdx.peepoo.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Npc {
    protected BinaryTree dialogueTree;
    protected BinaryTree.Node currentNode;

    public Sprite getSprite() {
        return sprite;
    }

    protected Sprite sprite;

    int x;
    int y;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BinaryTree.Node getCurrentNode() {
        return currentNode;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
