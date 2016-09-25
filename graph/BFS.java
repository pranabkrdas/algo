package com.home.latest.graph;

import com.home.latest.ds.Queue;
import com.home.latest.ds.QueueImpl;
import com.home.latest.ds.Stack;
import com.home.latest.ds.StackImpl;

/**
 * Created by pranabdas on 1/13/16.
 */
public class BFS {

    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s;

    public BFS(Graph G, int s){
        int V = G.V();
        this.s = s;
        marked = new boolean[V];
        edgeTo = new int[V];
        distTo = new int[V];
        Queue<Integer> queue = new QueueImpl<>();
        queue.enqueue(s);
        marked[s] = true;
        edgeTo[s] = s;
         while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Stack<Integer> pathTo(int v){
        Stack<Integer> result = new StackImpl<>();
        for(int x = v; x!=s; x=edgeTo[x]){
            result.push(x);
        }
        result.push(s);
        return result;
    }

    public void getDist(){
        System.out.println();
        for(int i=0; i<distTo.length ; i++){
            System.out.println(i + " = " + distTo[i]);
        }
    }

    public static void main(String[] args){
        Graph G = new UnDiGraph(6);
        G.addEdge(0, 2);
        G.addEdge(0, 1);
        G.addEdge(0, 5);
        G.addEdge(1, 2);
        G.addEdge(2, 4);
        G.addEdge(5, 3);
        G.addEdge(4, 3);
        G.printGraph();
        BFS d1 = new BFS(G, 0);
        System.out.println(d1.hasPathTo(4));
        for(Integer edge : d1.pathTo(4)){
            System.out.print(" " + edge + " ");
        }
        d1.getDist();


        Graph G1 = new DiGraph(6);
        G1.addEdge(5, 0);
        G1.addEdge(2, 4);
        G1.addEdge(3, 2);
        G1.addEdge(1, 2);
        G1.addEdge(0, 1);
        G1.addEdge(4, 3);
        G1.addEdge(3, 5);
        G1.addEdge(0, 2);
        G1.printGraph();
        BFS d2 = new BFS(G1, 0);
        System.out.println(d2.hasPathTo(4));
        for(Integer edge : d2.pathTo(4)){
            System.out.print(" " + edge + " ");
        }
        d2.getDist();

    }
}
