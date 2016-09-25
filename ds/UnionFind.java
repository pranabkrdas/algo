package com.home.latest.ds;

/**
 * Created by pranabdas on 6/17/16.
 */
public class UnionFind {

    private int[] id;
    private int[] size;

    public UnionFind(int N){
        for(int i=0; i<N; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q){
        int root_p = root(p);
        int root_q = root(q);
        if(size[root_p] > size[root_q]){
            id[root_q] = root_p;
            size[root_p] += size[root_q];
        }
        else{
            id[root_p] = root_q;
            size[root_q] += size[root_p];
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public int root(int p){
        while(p != id[p]){
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

}
