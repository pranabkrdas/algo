package com.home.latest.tree;

/**
 * Left leaning RBT
 * No node has 2 Red links connected to it.
 * Red links lean left.
 * Number of black links from root to any leaf node are same.
 * New link are red.
 * Created by pranabdas on 5/28/16.
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    Node root;
    private static final boolean RED = true;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;
        int count;
        K successor;
        K predecessor;
        K min;
        K max;

        public Node(K elem, V val){
            key = elem;
            value = val;
            color = RED;
        }
    }

    public Node rotateLeft(Node parent){
        assert isRed(parent.right);
        Node rightChild = parent.right;
        parent.right = rightChild.left;
        rightChild.left = parent;
        rightChild.color = parent.color;
        parent.color = RED;
        return rightChild;
    }

    public Node rotateRight(Node parent){
        assert isRed(parent.left) && isRed(parent.left.left);
        Node leftChild = parent.left;
        parent.left = leftChild.right;
        leftChild.right = parent;
        leftChild.color = parent.color;
        parent.color = RED;
        return leftChild;
    }

    public void flipColors(Node parent){
        assert !isRed(parent);
        assert isRed(parent.left);
        assert isRed(parent.right);
        parent.left.color = parent.color;
        parent.right.color = parent.color;
        parent.color = RED;
    }

    public V get(K key){
        if(root == null) return null;
        Node x = root;
        while(x != null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void put(K key, V val){
        if(root == null){
            root = new Node(key, val);
            return;
        }
        root = put(root, key, val);
    }

    public Node put(Node x, K key, V val){
        if(x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if(cmp > 0) x.right = put(x.right, key, val);
        else x.value = val;

        if(isRed(x.right)) x = rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if(!isRed(x) && isRed(x.left) && isRed(x.right)) flipColors(x);

        return x;
    }

    public Node min(){
        return min(root);
    }

    public Node min(Node x){
        if(null == x) return null;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    public Node max(){
        return max(root);
    }

    public Node max(Node x){
        if(null == x) return null;
        while(x.right != null){
            x = x.right;
        }
        return x;
    }

    public K successor(K key){
        if(null == root) return null;
        Node x = successor(root, key, null);
        if(x == null) return null;
        return x.key;
    }

    public Node successor(Node x, K key, Node parent){
        if(x == null) return null; // key not found
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return successor(x.left, key, x);
        }
        else if(cmp > 0){
            return successor(x.right, key, parent);
        }
        else{
            if(x.right != null) return min(x.right);
            else return parent;
        }
    }

    public K predecessor(K key){
        if(null == root) return null;
        Node x = predecessor(root, key, null);
        if(x == null) return null;
        return x.key;
    }

    public Node predecessor(Node x, K key, Node parent){
        if(x == null) return null; // key not found
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return predecessor(x.left, key, parent);
        }
        else if(cmp > 0){
            return predecessor(x.right, key, x);
        }
        else{
            if(x.left != null) return max(x.left);
            else return parent;
        }
    }


    public boolean isRed(Node node){
        return node.color;
    }

}
