package com.home.latest.tree;

import com.home.old.tree.MinPQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Left leaning Red Black BST
 * No node have 2 red links connected to it.
 * Number of black links from root to any leaf node are same.
 * Red links lean left.
 * New links are red.
 * Created by pranabdas on 6/11/16.
 */
public class OneDRangeSearch<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        K key;
        V value;
        boolean color = RED;
        Node left;
        Node right;
        int count;

        public Node(K k, V v, int count){
            this.key = k;
            this.value = v;
            this.count = count;
        }
    }

    public V get(K key){
        if(root == null) return null;
        Node x = root;
        int cmp = key.compareTo(x.key);
        while(x != null){
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public Node rotateLeft(Node parent){
        assert parent.left.color == BLACK;
        assert parent.right.color == RED;
        Node rightChild = parent.right;
        parent.right = rightChild.left;
        rightChild.left = parent;
        rightChild.color = parent.color;
        parent.color = RED;
        parent.count = 1 + size(parent.left) + size(parent.right);
        return rightChild;
    }

    public Node rotateRight(Node parent){
        assert parent.left.color == RED && parent.left.left.color == RED;
        Node leftChild = parent.left;
        parent.left = leftChild.right;
        leftChild.right = parent;
        leftChild.color = parent.color;
        parent.color = RED;
        parent.count = 1 + size(parent.left) + size(parent.right);
        return leftChild;
    }

    public void flipColors(Node parent){
        assert parent.color = BLACK;
        assert parent.left.color == RED && parent.right.color == RED;
        parent.left.color = BLACK;
        parent.right.color = BLACK;
        parent.color = RED;
    }

    public void put(K key, V value){
        root = put(root, key, value);
    }

    public Node put(Node x, K key, V value){
        if(x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, value);
        else if(cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        if(isRed(x.right)) x = rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public boolean isRed(Node x){
        return x.color;
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x == null) return 0;
        return x.count;
    }

    public void deleteMin(){
        if(root == null) return;
        root = deleteMin(root);
    }

    public Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax(){
        if(root == null) return;
        root = deleteMax(root);
    }

    public Node deleteMax(Node x){
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Node min(){
        if(root == null) return null;
        return min(root);
    }

    public Node min(Node x){
        while(x.left !=  null) x = x.left;
        return x;
    }

    public Node max(){
        if(root == null) return null;
        return max(root);
    }

    public Node max(Node x){
        while(x.right != null) x = x.right;
        return x;
    }

    public int rank(K key){
       return rank(root, key);
    }

    public int rank(Node x, K key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(x.left, key);
        else if(cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    public K successor(K key){
        if(root == null) return null;
        Node x = successor(root, root, key);
        if(x == null) return null;
        return x.key;
    }

    public Node successor(Node x, Node parent, K key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return successor(x.left, x, key);
        }
        else if(cmp > 0){
            return successor(x.right, parent, key);
        }
        else{
            if(x.right != null) return min(x.right);
            return parent;
        }
    }

    public K predecessor(K key){
        if(key == null) return null;
        Node x = predecessor(root, root, key);
        if(x == null) return null;
        return x.key;
    }

    public Node predecessor(Node x, Node parent, K key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return predecessor(x.left, parent ,key);
        }
        else if(cmp > 0){
            return predecessor(x.right, x, key);
        }
        else{
            if(x.left != null) return max(x.left);
            return parent;
        }
    }

    public boolean contains(K key){
        return get(key) != null;
    }

    public int rangeCount(K key1, K key2){
        if(contains(key2)) return rank(key2) - rank(key1) + 1;
        else return rank(key2) - rank(key1);
    }

    public List<K> rangeSearch(K key1, K key2){
        List<K> result = new ArrayList<>();
        rangeSearch(root, key1, key2, result);
        return result;
    }

    public void rangeSearch(Node x, K key1, K key2, List<K> result){
        if(x == null) return;
        if(x.key.compareTo(key1) < 0) return;
        if(x.key.compareTo(key2) > 0) return;
        rangeSearch(x.left, key1, key2, result);
        if(x.key.compareTo(key1) >= 0 && x.key.compareTo(key2) <= 0) result.add(x.key);
        rangeSearch(x.right, key1, key2, result);
    }

    public void delete(K key){
        root = delete(root, key);
    }

    public Node delete(Node x, K key){
        if(x == null) return null;
        int result = key.compareTo(x.key);
        if(result < 0) x.left = delete(x.left, key);
        else if(result > 0) x.right = delete(x.right, key);
        else{
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

}
