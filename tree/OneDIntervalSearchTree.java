package com.home.latest.tree;

/**
 * Created by pranabdas on 11/26/15.
 */
public class OneDIntervalSearchTree {

    private class Interval{
        int low, high;
    }

    private class Node{
        Interval interval;
        int max;
        Node left;
        Node right;
    }


}
