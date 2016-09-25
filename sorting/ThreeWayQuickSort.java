package com.home.latest.sorting;

/**
 * Created by pranabdas on 12/14/15.
 */
public class ThreeWayQuickSort {

    public static void sort(Comparable[] a){
        KnuthShuffle.shuffle(a);
        SortUtil.printInput(a);
        sort(a, 0, a.length-1);
        assert SortUtil.isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if(lo >= hi) return;
        int lt = lo;
        int gt = hi;
        int i = lo;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                SortUtil.exch(a, lt++, i++);
            }
            else if(cmp > 0){
                SortUtil.exch(a, i, gt--);
            }
            else{
                i++;
            }
        }
        SortUtil.printInput(a);
        System.out.println("lt=" + lt + ", gt=" + gt);
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    public static void main(String[] args){
        Integer[] inp = {7, 8, 1, 12, 3, 2, 5, -6, 101, 0, 56, 43};
        sort(inp);
        SortUtil.printInput(inp);
    }
}
