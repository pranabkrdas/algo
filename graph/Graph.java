package com.home.latest.graph;

/**
 * Created by pranabdas on 1/19/16.
 */
public interface Graph {

    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    int V();

    int E();

    void printGraph();
}
