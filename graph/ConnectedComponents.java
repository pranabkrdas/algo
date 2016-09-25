package com.home.latest.graph;

/**
 * Created by pranabdas on 1/13/16.
 */
public class ConnectedComponents {

    private int[] id;
    private boolean[] marked;
    private int count;

    public ConnectedComponents(UnDiGraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                ++count;
                dfs(G, v);
            }
        }
    }

    public void dfs(UnDiGraph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return id[v];
    }

    public static void main(String[] args){
        UnDiGraph G = new UnDiGraph(13);
        G.addEdge(0, 6);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 5);
        G.addEdge(4, 6);
        G.addEdge(3, 4);
        G.addEdge(5, 3);
        G.addEdge(5, 4);
        G.addEdge(7, 8);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(9, 12);
        G.addEdge(11, 12);
        G.printGraph();
        ConnectedComponents cc = new ConnectedComponents(G);
        System.out.println(cc.count());
        System.out.println(cc.id(3));
        System.out.println(cc.id(8));
        System.out.println(cc.id(11));
    }
}
