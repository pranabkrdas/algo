package com.home.latest.tree;

/**
 * Created by pranabdas on 6/11/16.
 */
public class TwoDKdTree {

    private Node root;

    private class Point{
        int x;
        int y;

        public Point(int x1, int y1){
            this.x = x1;
            this.y = y1;
        }
    }

    private class Node{
        Point p;
        Node left;
        Node right;

        public Node(Point p1){
            this.p = p1;
        }
    }

    public void put(int x, int y){
        root = put(root, x, y, true);
    }

    public Node put(Node n, int x, int y, boolean yAxis){
        if(n == null){
            Point p = new Point(x, y);
            return new Node(p);
        }
        if(yAxis){
            if(x < n.p.x) n.left = put(n.left, x, y, false);
            else n.right = put(n.right, x, y, false);
        }
        else{
            if(y < n.p.y) n.left = put(n.left, x, y, true);
            else n.right = put(n.right, x, y, true);
        }
        return n;
    }
}
