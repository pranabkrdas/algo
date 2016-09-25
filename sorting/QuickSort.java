package com.home.latest.sorting;

import com.home.latest.Util;

/**
 * Created by pranabdas on 12/8/15.
 */
public class QuickSort {

    private static final int CUT_OFF = 10;

    public static void sort(Comparable[] a){
        KnuthShuffle.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int partition = partition(a, lo, hi);
        sort(a, lo, partition-1);
        sort(a, partition+1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        while(true){
            while(a[++i].compareTo(a[lo]) < 0) {
                if(i == hi) break;
            }
            while(a[--j].compareTo(a[lo]) > 0){
                if(j == lo) break;
            }
            if(i >= j) break;
            Util.exch(a, i, j);
        }
        Util.exch(a, j, lo);
        return j;
    }

}
