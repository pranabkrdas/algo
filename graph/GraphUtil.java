package com.home.latest.graph;

/**
 * Created by pranabdas on 1/7/16.
 */
public class GraphUtil {

    public static int degree(UnDiGraph G, int v){
        int degree = 0;
        for(int w : G.adj(v)){
            degree++;
        }
        return degree;
    }

    public static int maxDegree(UnDiGraph G){
        int maxDegree = 0;
        for(int v=0; v<G.V(); v++){
            int degree = degree(G, v);
            if(degree > maxDegree) maxDegree = degree;
        }
        return maxDegree;
    }

    public static double averageDegree(UnDiGraph G){
        return 2 * G.E()/G.V();
    }

    public static int numberOfSelfLoops(UnDiGraph G){
        int selfLoops = 0;
        for(int v=0; v<G.V(); v++){
            for(int w : G.adj(v)){
                if(v == w) selfLoops++;
            }
        }
        return selfLoops/2;
    }
}
