package com.home.latest.graph;

import java.util.List;
import java.util.ArrayList;
/**
 * This implementation is for Un-Directed Graph
 * Created by pranabdas on 7/23/16.
 */
public class EdgeWeightedGraph {

    private List<Edge>[] adj;
    private int V;
    private int E;

    public EdgeWeightedGraph(int V){
        this.V = V;
        adj = (ArrayList<Edge>[])new ArrayList[this.V];
        for(int i=0; i<V; i++){
            adj[i] = new ArrayList();
        }
    }

    public int V(){
        return this.V;
    }

    /**
     * number of edges in this graph
     * @return
     */
    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    /**
     * edges incident to v
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    /**
     * all edges in this graph
     * @return
     */
    public Iterable<Edge> edges(){
        List<Edge> edges = new ArrayList<Edge>();
        for(int v=0; v<V; v++){
            for(Edge e : adj(v)){
                if(e.other(v) > v) edges.add(e);
            }
        }
        return edges;
    }
}
