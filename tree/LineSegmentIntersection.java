package com.home.latest.tree;

import com.home.old.tree.MinPQ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pranabdas on 6/23/16.
 */
public class LineSegmentIntersection {

    public class Point implements Comparable<Point>{
        int x;
        int y;
        boolean horizontal;
        boolean leftEndPoint;

        public Point(int x1, int y1, boolean horizontal, boolean leftEndPoint){
            this.x = x1;
            this.y = y1;
            this.horizontal = horizontal;
            this.leftEndPoint = leftEndPoint;
        }

        public int compareTo(Point p){
            if(p.x < this.x) return -1;
            else if(p.x > this.x) return 1;
            else return 0;
        }

        public boolean isLeftEndPoint(){
            return this.leftEndPoint;
        }

        public boolean isVertical(){
            return !this.horizontal;
        }
    }

    public Map<Integer, List<Integer>> lineSweepAlgo(List<Point> points){
        OneDRangeSearch<Integer, Integer> bst = new OneDRangeSearch();
        Map<Integer, List<Integer>> result = new HashMap<>();
        MinPQ<Point> pq = new MinPQ<>();
        for(Point pt : points){
            pq.insert(pt);
        }
        while(!pq.isEmpty()){
            Point pt = pq.delMin();
            if(!pt.isVertical()){
                if(pt.isLeftEndPoint()){
                    bst.put(pt.y, null);
                }
                else{
                    bst.delete(pt.y);
                }
            }
            else{
                if(!pq.isEmpty()){
                    Point pt2 = pq.delMin();
                    // intersections are y-coordinates
                    List<Integer> intersections = bst.rangeSearch(pt.y, pt2.y);
                    List<Integer> existingIntersections = result.get(pt.x);
                    if(existingIntersections == null || existingIntersections.isEmpty()){
                        result.put(pt.x, intersections);
                    }
                    else{
                        existingIntersections.addAll(intersections);
                    }
                }
            }
        }

        return result;
    }
}
