package com.home.latest.tree;

import com.home.latest.ds.LinkedList;
import com.home.old.ds.queue.Queue;
import com.home.old.ds.queue.QueueImpl;

public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value>{

    private Node<Key, Value> root;

    public Value get(Key key){
        Node<Key, Value> x = root;
        while( x != null ){
            int result = key.compareTo(x.key);
            if( result < 0 ) x = x.left;
            else if(result > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public Node<Key, Value> getNode(Key key){
        if(key == null) return null;
        Node<Key, Value> x = root;
        while(x != null){
            int cmp = key.compareTo(x.key);
            if(cmp == 0) return x;
            if(cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    public void addLeft(Key parent, Key key, Value val){
        Node x = getNode(parent);
        if(x == null) return;
        if(x.left == null){
            x.left = new Node(key, val, 1);
        }
        else{
            x.left.key = key;
            x.left.value = val;
        }
    }

    public void addRight(Key parent, Key key, Value val){
        Node x = getNode(parent);
        if(x == null) return;
        if(x.right == null){
            x.right = new Node(key, val, 1);
        }
        else{
            x.right.key = key;
            x.right.value = val;
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    public Node put(Node<Key, Value> x, Key key, Value val){
        if(x == null) return new Node(key, val, 1);
        int result = key.compareTo(x.key);
        if(result < 0) x.left = put(x.left, key, val);
        else if(result > 0) x.right = put(x.right, key, val);
        else x.value = val;
        x.count = 1 + x.left.count + x.right.count;
        return x;
    }

    public Key min(){
        if(root == null) return null;
        return min(root).key;
    }

    public Node<Key, Value> min(Node<Key, Value> x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        if(root == null) return null;
        return max(root).key;
    }

    public Node<Key, Value> max(Node x){
        if(x.right == null) return x;
        return max(x.right);
    }

    /**
     * largest key <= given key
     * smallest key in right subtree
     * @param key
     * @return
     */
    public Key floor(Key key){
        Node<Key, Value> x = floor(root, key);
        if(x == null) return null;
        return x.key;
    }

    public Node floor(Node<Key, Value> x, Key key){
        if(x == null) return null;
        int result = key.compareTo(x.key);
        if(result == 0) return x;
        else if(result < 0) return floor(x.left, key);

        Node t = floor(x.right, key);
        if(t != null) return t;
        else return x;
    }

    public Key ceiling(Key key){
        Node<Key, Value> x = ceiling(root, key);
        if(x == null) return null;
        return x.key;
    }

    public Node ceiling(Node<Key, Value> x, Key key){
        if(x == null) return null;
        int result = key.compareTo(x.key);
        if(result == 0) return x;
        if(result > 0) return floor(x.right, key);

        Node t = floor(x.left, key);
        if(t != null) return t;
        else return x;
    }

    public int size(){
        return size(root);
    }

    /**
     * sum of left sub-tree nodes, right sub-tree nodes and itself.
     * @param x
     * @return
     */
    public int size(Node x){
        if(x == null) return 0;
        return x.count;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    /**
     * range count between given 2 keys
     * @param lo
     * @param hi
     * @return
     */
    public int size(Key lo, Key hi){
        if(contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public int rank(Key key){
        return rank(root, key);
    }

    /**
     * number of keys < given key
     * @param x
     * @param key
     * @return
     */
    public int rank(Node<Key, Value> x, Key key){
        if(x == null) return 0;
        int result = key.compareTo(x.key);
        if(result < 0) return rank(x.left, key);
        else if(result > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    /**
     * key of rank k
     * @param k
     * @return
     */
    public Key select(int k){
        if(root == null) return null;
        return select(root, k).key;
    }

    public Node<Key, Value> select(Node<Key, Value> x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if(t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k-t-1);
        else return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new QueueImpl<Key>();
        inorder(root, q);
        return q;
    }

    /**
     * range search between given 2 keys
     * @param lo
     * @param hi
     * @return
     */
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new QueueImpl<Key>();
        keys(root, q, lo, hi);
        return q;
    }

    public void keys(Node<Key, Value> x, Queue<Key> q, Key lo, Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, q, lo, hi);
        if(cmplo <= 0 && cmphi >=0) q.enqueue(x.key);
        if(cmphi > 0) keys(x.right, q, lo, hi);
    }

    public void inorder(Node<Key, Value> x, Queue<Key> q){
        if(x == null) return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    public void deleteMin(){
        if( root == null ) return;
        root = deleteMin(root);
    }

    public Node deleteMin(Node x){
        if(x.left == null) return x.right;
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

    public void delete(Key key){
        root = delete(root, key);
    }

    public Node delete(Node<Key, Value> x, Key key){
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

    /**
     * public void delete kth smallest element.
     * if the count in present node is gt k, go to left child
     * else subtract the current node's count from k and search for new k in right child
     * @param k
     */
    public void delete(int k){
        delete(root, k);
    }

    public void delete(Node<Key, Value> x, int k){
        if(size(x) == k){
            delete(x.key);
        }
        else if(size(x) > k){
            delete(x.left, k);
        }
        else{
            delete(x.right, k - size(x));
        }
    }

    public Key predecessor(Key key){
        Node<Key, Value> x = predecessor(root, key, null);
        if(x != null) return x.key;
        return null;
    }

    public Node<Key, Value> predecessor(Node<Key, Value> x, Key key, Node<Key, Value> parent){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            if(x.left == null) return parent;
            else return max(x.left);
        }
        else if(cmp < 0){
            return predecessor(x.left, key, parent);
        }
        else{
            return predecessor(x.right, key, x);
        }
    }

    public Key successor(Key key){
        Node<Key, Value> x = successor(root, key, null);
        if(x != null) return x.key;
        return null;
    }

    public Node<Key, Value> successor(Node<Key, Value> x, Key key, Node<Key, Value> parent){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            if(x.right != null) return min(x.right);
            else return parent;
        }
        else if(cmp < 0){
            return successor(x.left, key, x);
        }
        else{
            return successor(x.right, key, parent);
        }
    }

    /**
     * Return true if key is in the BST. Same as contains();
     * @param key
     * @return
     */
    public boolean member(Key key){
        return get(key) != null;
    }

}