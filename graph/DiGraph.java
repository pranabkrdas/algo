package com.home.latest.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranabdas on 1/19/16.
 */
public class DiGraph implements Graph{

    private List<Integer>[] adj;
    private final int V;

    public DiGraph(int V){
        this.V = V;
        adj = (ArrayList<Integer>[])new ArrayList[V];
        for(int i=0; i<V; i++){
            adj[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E(){
        int edgeCount = 0;
        for(List edges : adj){
            edgeCount += edges.size();
        }
        return edgeCount;
    }

    public DiGraph reverse(){
        DiGraph R = new DiGraph(V);
        for(int v=0; v<V; v++){
            for(int w : adj[v]){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public void printGraph(){
        for(int v=0; v<V; v++){
            for(int w : adj(v)){
                System.out.println(v + "-" + w);
            }
        }
    }
}
