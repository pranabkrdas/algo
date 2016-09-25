package com.home.latest.trie;

import com.home.latest.ds.Queue;
import com.home.latest.ds.QueueImpl;

/**
 * Created by pranabdas on 5/30/16.
 */
public class Trie<V> {

    private static final int R = 256;
    private Node root = new Node();

    private class Node{
        V value;
        Node[] next;

        public Node(){
            next = (Node[]) new Object[R];
        }

        public Node(V val){
            this.value = val;
            next = (Node[]) new Object[R];
        }
    }

    public void put(String key, V value){
        if(key == null || key.length() == 0) return;
        root = put(root, key, value, 0);
    }

    public Node put(Node x, String key, V value, int len){
        if(x == null) x = new Node();
        if(len == key.length()){
            x.value = value;
            return x;
        }
        char c = key.charAt(len);
        x.next[c] = put(x.next[c], key, value, len+1);
        return x;
    }

    public V get(String key){
        Node x = get(root, key, 0);
        if(x == null) return null;
        return x.value;
    }

    public Node get(Node x, String key, int len){
        if(x == null) return null;
        if(key.length() == len) return x;
        char c = key.charAt(len);
        return get(x.next[c], key, len+1);
    }

    public void delete(String key){
        root = delete(root, key, 0);
    }

    public Node delete(Node x, String key, int len){
        if(x == null) return null;
        if(len == key.length()) {
            x.value = null;
        }
        else{
            int c = key.charAt(len);
            x.next[c] = delete(x.next[c], key, len+1);
        }
        if(x.value != null) return x;
        for(int r=0; r<R; r++){
            if(x.next[r] != null) return x;
        }
        return null;
    }

    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix){
        Queue<String> queue = new QueueImpl<>();
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }

    public void collect(Node x, String prefix, Queue<String> queue){
        if(x == null) return;
        if(x.value != null) queue.enqueue(prefix);
        for(int i=0; i<R; i++){
            collect(x.next[i], prefix+i, queue);
        }
    }

    /**
     * Wild character match
     * @param pat
     * @return
     */
    public Iterable<String> keysThatMatch(String pat){
        Queue<String> queue = new QueueImpl<>();
        match(root, "", pat, queue);
        return queue;
    }

    public void match(Node x, String prefix, String pat, Queue<String> q){
        if(x == null) return;
        if(prefix.length() == pat.length() && x.value != null) q.enqueue(prefix);
        if(prefix.length() == pat.length()) return;
        for(int i=0; i<R; i++){
            char c = pat.charAt(prefix.length());
            if(c == '.' || c == i){
                match(x.next[i], prefix+i, pat, q);
            }
        }
    }

    public String longestPrefixOf(String key){
        int len = search(root, key, 0, 0);
        return key.substring(0, len);
    }

    public int search(Node x, String key, int d, int len){
        if(x == null) return len;
        if(x.value != null) len = d;
        if(key.length() == d) return len;
        int c = key.charAt(d);
        return search(x.next[c], key, d+1, len);
    }

}
