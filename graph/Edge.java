package com.home.latest.graph;

/**
 * Created by pranabdas on 7/23/16.
 */
public class Edge implements Comparable<Edge> {

    private final double weight;
    private final int v, w;

    public Edge(int v, int w, double wgt){
        this.v = v;
        this.w = w;
        this.weight = wgt;
    }

    public int either(){
        return v;
    }

    public int other(int v){
        if(this.v == v) return w;
        else if(this.w == w) return v;
        else throw new RuntimeException("Inconsistent Edge");
    }

    public int compareTo(Edge that){
        if(this.weight > that.weight) return 1;
        else if(this.weight < that.weight) return -1;
        else return 0;
    }

    public double weight(){
        return this.weight;
    }

}