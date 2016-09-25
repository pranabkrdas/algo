package com.home.latest.graph;

import com.home.latest.ds.Stack;
import com.home.latest.ds.StackImpl;

/**
 * Created by pranabdas on 1/19/16.
 */
public class TopologicalSort {
    private Stack<Integer> reversePost = new StackImpl();
    private boolean[] marked;

    public TopologicalSort(Graph G){
        marked = new boolean[G.V()];
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs_r(G, v);
            }
        }
    }

    public void dfs_r(Graph G, int v){
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs_r(G, w);
            }
        }
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
