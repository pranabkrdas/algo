package com.home.latest.graph;

import com.home.latest.ds.Queue;
import com.home.latest.ds.QueueImpl;
import com.home.latest.ds.UnionFind;
import com.home.old.priorityqueue.MinPQ;

/**
 * Created by pranabdas on 7/23/16.
 */
public class KruskalMST {
    private final Queue<Edge> mst = new QueueImpl();
    private int W;

    public KruskalMST(EdgeWeightedGraph G){
        MinPQ<Edge> pq = new MinPQ(G.E());
        for(Edge e : G.edges()){
            pq.insert(e);
        }
        UnionFind uf = new UnionFind(G.V());
        while(!pq.isEmpty() && mst.size() < G.V()){
            Edge e = pq.deleteMin();
            int v = e.either();
            int w = e.other(v);
            if(!uf.connected(v, w)){
                mst.enqueue(e);
                uf.union(v, w);
                W += e.weight();
            }
        }
    }

    /**
     * edges in Minimum Spanning Tree
     * @return
     */
    public Iterable<Edge> edges(){
        return mst;
    }

    /**
     * weight of MST
     * @return
     */
    public double weight(){
        return W;
    }
}
