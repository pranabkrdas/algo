package com.home.latest.graph;

import com.home.latest.ds.Stack;
import com.home.latest.ds.StackImpl;

/**
 * Created by pranabdas on 7/21/16.
 */
public class DirectedCycle {

    private Stack<Integer> cycle;
    private boolean hasCycle;
    private boolean[] onStack;
    private boolean[] marked;
    private int[] parent;

    public void detectCycle(DiGraph G){
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        parent = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs_r(G, v);
            }
        }
    }

    public void dfs_r(DiGraph G, int v){
        marked[v] = true;
        onStack[v] = true;
        for(int w : G.adj(v)){
            if(hasCycle) return;
            if(!marked[w]){
                parent[w] = v;
                dfs_r(G, w);
            }
            else if(onStack[w]){
                hasCycle = true;
                cycle = new StackImpl();
                for(int x=v; x!=w; x=parent[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }

        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

}
