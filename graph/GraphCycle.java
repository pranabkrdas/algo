package com.home.latest.graph;

/**
 * Created by pranabdas on 1/14/16.
 */
public class GraphCycle {

    private boolean[] marked;
    private boolean hasCycle;

    public GraphCycle(UnDiGraph G){
        marked = new boolean[G.V()];
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs_r(G, s, s);
            }
        }
    }

    public void dfs_r(UnDiGraph G, int v, int s){
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs_r(G, w, v);
            }
            else{
                // w != parent[v] hasCycle = true
                if(w != s) hasCycle = true;
            }
        }
    }

    public boolean isDAG(){
        return !hasCycle;
    }
}
