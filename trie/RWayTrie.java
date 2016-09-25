package com.home.latest.trie;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Created by pranabdas on 12/7/15.
 */
public class RWayTrie<Value> {

    private static final int R = 256;
    private Node root = new Node();

    private static class Node{
        private Object value;
        private Node[] next = new Node[R];
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null) return null;
        return (Value)x.value;
    }

    public Node get(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value value){
        root = put(root, key, value, 0);
    }

    public Node put(Node x, String key, Value value, int d){
        if(x == null) x = new Node();
        if(key.length() == d){
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d+1);
        return x;
    }

    public void delete(String key){
        root = delete(root, key, 0);
    }

    public Node delete(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()){
            x.value = null;
        }
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if(x.value != null) return x;
        for(char c=0; c<R; c++){
            if(x.next[c] != null) return x;
        }
        return null;
    }

    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> q = new ArrayDeque<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    public void collect(Node x, String pre, Queue<String> q){
        if(x == null) return;
        if(x.value != null) q.add(pre);
        for(char c=0; c<R; c++){
            collect(x.next[c], pre+c, q);
        }
    }

    public Iterable<String> keysThatMatch(String pat){
        Queue<String> q = new ArrayDeque();
        collect(root, q, pat, "");
        return q;
    }

    public void collect(Node x, Queue<String> q, String pat, String pre){
        if(x == null) return;
        int d = pre.length();
        if(d == pat.length()){
            if(x.value != null){
                q.add(pre);
            }
        }
        else {
            char c = pat.charAt(d);
            if (c == '.') {
                for (char ch = 0; ch < R; ch++) {
                    collect(x.next[ch], q, pat, pre + c);
                }
            }
            else{
                collect(x.next[c], q, pat, pre+c);
            }
        }
    }

    public String longestPrefix(String query){
        int length = longestPrefix(root, query, 0, 0);
        return query.substring(0, length);
    }

    public int longestPrefix(Node x, String query, int d, int length){
        if(x == null) return length;
        if(x.value != null) length = d;
        if(d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefix(x.next[c], query, d+1, length);
    }

    public static void main(String[] args){

    }
}
