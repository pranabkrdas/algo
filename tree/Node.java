package com.home.latest.tree;

/**
 * Created by pranabdas on 12/4/15.
 */
public class Node<Key extends Comparable<Key>, Value> {
    public Key key;
    public Value value;
    public Node left;
    public Node right;
    public int count;

    public Node(Key key, Value val, int count){
        this.key = key;
        this.value= val;
        this.count = count;
    }

    @Override
    public String toString(){
        return key+"";
    }
}
