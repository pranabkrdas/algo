package com.home.latest.graph;

import com.home.latest.ds.Stack;

/**
 * Created by pranabdas on 1/27/16.
 */
public class StronglyConnected {
    public int[] scc;
    public boolean[] marked;
    public int components;

    /**
     * Kosaraju-Sharir 2 pass linear algo
     * 1st pass is on a reversed Graph and calculating a reverse post order.
     * 2nd pass is on G with considering vertices in reverse post order.
     * @param G
     */
    public StronglyConnected(DiGraph G){
        scc = new int[G.V()];
        marked = new boolean[G.V()];
        TopologicalSort ts = new TopologicalSort(G.reverse());
        for(int v : ts.reversePost()){
            if(!marked[v]){
                dfs_r(G, v);
                components++;
            }
        }
    }

    public void dfs_r(DiGraph G, int v){
        marked[v] = true;
        scc[v] = components;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs_r(G, w);
            }
        }
    }

    public int component(int v){
        return scc[v];
    }

    public boolean isStronglyConnected(int v, int w){
        return scc[v] == scc[w];
    }
}
