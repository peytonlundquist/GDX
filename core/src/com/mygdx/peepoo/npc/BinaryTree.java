package com.mygdx.peepoo.npc;

import java.util.LinkedList;
import java.util.Random;

public class BinaryTree {

    public static void main(String[] args){

        BinaryTree bt = new BinaryTree();
        bt.add(null, "hello!", "hi", "yo");
        bt.add("hi", "whats up!", "nuffin", "idk");
        bt.add("yo", "whats down!", "huh?", "idk");


        System.out.println(bt.rootNode.message);
        System.out.println(bt.traverseLeft(bt.rootNode).message);
        System.out.println(bt.traverseRight(bt.rootNode).message);
    }

    private Node rootNode;

    public BinaryTree(){}


    public Node traverseLeft(Node node){
        return node.leftNode;
    }

    public Node traverseRight(Node node){
        return node.rightNode;
    }

    public Node getRoot(){
        return rootNode;
    }

    public void add(String parentResponse, String message, String leftResponse, String rightResponse){
        if(rootNode == null){
            rootNode = new Node(message, leftResponse, rightResponse);
            return;
        }

        LinkedList<Node> stack = new LinkedList<>();
        stack.push(rootNode);
        Node currentNode;

        while(!stack.isEmpty()){
            currentNode = stack.pop();
            if(currentNode.rightResponse.equals(parentResponse)){
                if(currentNode.rightNode != null) throw new RuntimeException("Add failed. Message " + currentNode.rightNode.message + "exists");
                currentNode.rightNode = new Node(message, leftResponse, rightResponse);
                return;
            }
            if(currentNode.leftResponse.equals(parentResponse)){
                if(currentNode.leftNode != null) throw new RuntimeException("Add failed. Message " + currentNode.leftNode.message + "exists");
                currentNode.leftNode = new Node(message, leftResponse, rightResponse);
                return;
            }

            if(currentNode.leftNode == null && currentNode.rightNode == null){
                throw new RuntimeException("Could not find parent response " + parentResponse);
            }

            if(currentNode.leftNode == null){
                stack.push(currentNode.rightNode);
            }

            if(currentNode.rightNode == null){
                stack.push(currentNode.leftNode);
            }
        }

        throw new RuntimeException("Could not find parent response " + parentResponse);
    }

    public class Node{
        public Node leftNode;
        public Node rightNode;
        public String message;
        public String leftResponse;
        public String rightResponse;

        public Node(String message, String leftResponse, String rightResponse){
            this.message = message;
            this.leftResponse = leftResponse;
            this.rightResponse = rightResponse;
        }

        public void setLeftResponse(String leftResponse) {
            this.leftResponse = leftResponse;
        }

        public void setRightResponse(String rightResponse) {
            this.rightResponse = rightResponse;
        }

        public Boolean equals(String message){
            return this.message.equals(message);
        }
    }
}
