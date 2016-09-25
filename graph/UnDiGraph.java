package com.home.latest.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranabdas on 1/7/16.
 */
public class UnDiGraph implements Graph{

    private int V;
    private List<Integer>[] adj;

    public UnDiGraph(int n){
        this.V = n;
        adj = (List<Integer>[])new List[V];
        for(int i=0; i<n; i++){
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E(){
        int edges = 0;
        for(int i=0; i<V(); i++){
            edges += adj[i].size();
        }
        return edges/2;
    }

    public void printGraph(){
        for(int v=0; v<V(); v++){
            for(int w : adj(v)){
                System.out.println("v" + v + "-" + w);
            }
        }
    }
}
