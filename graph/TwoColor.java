package com.home.latest.graph;

/**
 * Created by pranabdas on 1/13/16.
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable;

    public TwoColor(UnDiGraph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        isTwoColorable = true;
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs_r(G, s);
            }
        }
    }

    public void dfs_r(UnDiGraph G, int v){
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w]){
                color[w] = !color[v];
                dfs_r(G, w);
            }
            else{
                if(color[v] == color[w]) isTwoColorable = false;
            }
        }
    }


    public boolean isBipartite(){
        return isTwoColorable;
    }

    public static void main(String[] args){
        UnDiGraph G = new UnDiGraph(7);
        G.addEdge(0, 6);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 5);
        G.addEdge(1, 3);
        G.addEdge(3, 2);
        G.addEdge(2, 4);
        G.addEdge(5, 4);
        G.addEdge(6, 4);
        G.printGraph();
        TwoColor twoColor = new TwoColor(G);
        System.out.println(twoColor.isBipartite());
    }

}
