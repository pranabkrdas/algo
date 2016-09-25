package com.home.latest.tree;


import java.util.Stack;

/**
 * Created by pranabdas on 11/26/15.
 */
public class BT<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root;

    public BT(Key key, Value val){
        root = new Node(key, val, 0);
    }

    public Node<Key, Value> get(Key key){
        if(root == null) return null;
        return get(root, key);
    }

    public Node<Key, Value> get(Node x, Key key){
        if(x == null) return null;
        if(x.key == key) return x;
        Node t;
        t = get(x.left, key);
        if(t != null) return t;
        t = get(x.right, key);
        return t;
    }

    public void addLeft(Key parent, Key key, Value val){
        Node x = get(parent);
        if(x != null){
            if(x.left != null){
                x.left.key = key;
                x.left.value = val;
            }
            else{
                x.left = new Node(key, val, 0);
            }
        }
    }

    public void addRight(Key parent, Key key, Value val){
        Node x = get(parent);
        if(x != null){
            if(x.right != null){
                x.right.key = key;
                x.right.value = val;
            }
            else{
                x.right = new Node(key, val, 0);
            }
        }
    }

    public void delete(Key key){
        if(key == null) return;
        root = delete(root, key);
    }

    public Node delete(Node x, Key key){
        if(x == null) return null;
        if(x.key == key){
            if(x.left == null){
                return x.right;
            }
            else if(x.right == null){
                return x.left;
            }
            else{
                while(x.left.left != null){
                    x = x.left;
                }
                Node t = x.left;
                x.left = null;
                return t;
            }
        }

        if(x.left != null){
            x.left = delete(x.left, key);
        }
        if(x.right != null){
            x.right = delete( x.right, key);
        }
        return null;
    }

    public void printBT(Node x){

    }

    public void levelOrderTraversal(){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node x = stack.pop();
            if(x != null){
                System.out.println(x.key);
                stack.push(x.left);
                stack.push(x.right);
            }
        }
    }
}
